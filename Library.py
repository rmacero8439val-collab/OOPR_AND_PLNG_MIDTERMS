from datetime import datetime
import math

while True:
    total_pages_input = input("How many pages does the book have? ")
    if total_pages_input.isdigit():
        total_pages = int(total_pages_input)
        if total_pages > 0:
            break
        print("Invalid input. Pages must be greater than 0.")
    else:
        print("Invalid input. Please enter numbers only.")

while True:
    pages_read_input = input("How many pages have you read? ")
    if pages_read_input.isdigit():
        pages_read = int(pages_read_input)
        if pages_read <= total_pages:
            break
        print(f"Invalid input. Pages read cannot exceed total pages ({total_pages}).")
    else:
        print("Invalid input. Please enter numbers only.")

while True:
    issue_date_str = input("Enter Issue Date (YYYY-M-D): ")
    if any(char.isalpha() for char in issue_date_str):
        print("Invalid input. Dates cannot contain alphabetical letters.")
        continue
    try:
        issue_date = datetime.strptime(issue_date_str, "%Y-%m-%d")
        break
    except ValueError:
        print("Invalid date format. Use YYYY-M-D.")

while True:
    due_date_str = input("Enter Due Date (YYYY-M-D): ")
    if any(char.isalpha() for char in due_date_str):
        print("Invalid input. Dates cannot contain alphabetical letters.")
        continue
    try:
        due_date = datetime.strptime(due_date_str, "%Y-%m-%d")
        if due_date > issue_date:
            break
        print("Invalid timeline. Due Date must be after the Issue Date.")
    except ValueError:
        print("Invalid date format. Use YYYY-M-D.")

while True:
    current_date_str = input("Enter Current Date (YYYY-M-D): ")
    if any(char.isalpha() for char in current_date_str):
        print("Invalid input. Dates cannot contain alphabetical letters.")
        continue
    try:
        current_date = datetime.strptime(current_date_str, "%Y-%m-%d")
        if current_date >= issue_date:
            break
        print("Invalid timeline. Current Date cannot be earlier than the Issue Date.")
    except ValueError:
        print("Invalid date format. Use YYYY-M-D.")

percentage_read = (pages_read / total_pages) * 100
days_remaining = (due_date - current_date).days
pages_left = total_pages - pages_read

if days_remaining > 0:
    pages_per_day = math.ceil(pages_left / days_remaining)
    print(f"\nYou have read {percentage_read:.0f}% of the book.")
    print(f"You have {days_remaining} day(s) remaining to return the book.")
    print(f"You still have {pages_left} page(s) left and you should read {pages_per_day} pages a day.")
else:
    overdue_days = abs(days_remaining)
    print(f"\nYou have read {percentage_read:.0f}% of the book.")
    print(f"Warning: The book is OVERDUE by {overdue_days} day(s)!")
    print(f"You still have {pages_left} page(s) left.")
