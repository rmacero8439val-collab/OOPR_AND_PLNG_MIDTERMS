import sys

while True:
    first_name = input("Enter first name: ").strip()
    last_name = input("Enter last name: ").strip()
    
    if not first_name or not last_name:
        print("Error: First name and last name cannot be empty. Please try again.\n")
        continue

    print(f"First name: {first_name}")
    print(f"Last name: {last_name}")

    full_name = first_name + " " + last_name
    print(f"Full name: {full_name}")

    print(f"First character: {full_name[0]}")
    print(f"Last character: {full_name[-1]}")

    try:
        full_name[0] = "D"
    except TypeError:
        print("Strings are immutable; individual characters")
        print("cannot be changed directly.")

    new_name = "D" + first_name[1:]
    print(f"New name: {new_name}")
    print(f"Original name: {first_name}")

    print("Characters in the full name:")
    for char in full_name:
        print(char)

    space_indices = [i for i, char in enumerate(full_name) if char == " "]
    
    if space_indices:
        sliced_first = full_name[:space_indices[0]]
        sliced_last = full_name[space_indices[-1] + 1:]
    else:
        sliced_first = first_name
        sliced_last = last_name

    print(f"Sliced first name: {sliced_first}")
    print(f"Sliced last name: {sliced_last}")

    while True:
        search_char = input("Enter one character to search: ")
            
        if len(search_char) == 1 and search_char in full_name:
            matching_indices = [str(i) for i, char in enumerate(full_name) if char == search_char]
            count = len(matching_indices)
            indices_str = ", ".join(matching_indices)
            
            print(f"Character found {count} time(s) in the name.")
            print(f"Character found at index positions: {indices_str}")
        
            for space_idx in space_indices:
                if space_idx > 0 and space_idx < len(full_name) - 1:
                    before_space = full_name[space_idx - 1]
                    after_space = full_name[space_idx + 1]
                    print(f'The blank line between {before_space} and {after_space} is the space in "{full_name}".')
            break
        elif len(search_char) != 1:
            print("Error: Please enter exactly one character.")
        else:
            print("Character not found in the name. Try another one.")
            
    print("\n" + "="*40 + "\n")