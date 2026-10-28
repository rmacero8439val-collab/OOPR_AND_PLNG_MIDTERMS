class Book:
    def __init__(self, title, author, price):
        self.title = title
        self.author = author
        self.price = price
    def __str__(self):
        return f"Book: {self.title}  |  Author: {self.author}\nPrice: PHP {self.price:.2f}"
class EBook(Book):
    def __init__(self, title, author, price, file_size_mb):
        super().__init__(title, author, price)
        self.file_size_mb = file_size_mb
    def __str__(self):
        return f"{super().__str__()}  |  File size: {self.file_size_mb} MB"
def main():
    books = [
        Book("Python Basics", "Maria Santos",  450.00),
        EBook("Learning OOP", "Ana Cruz", 300.00, 5),
        EBook("Learning Python", "Juan Cruz", 299.00, 12)
    ]
    
    for book in books:
        print(book)
        print()  
if __name__ == "__main__":
    main()




class Vehicle:
    def __init__(self, brand, daily_rate):
        self.brand = brand
        self.daily_rate = daily_rate

    def calculate_rental(self, days):
        return self.daily_rate * days

    def __str__(self):
        return f"Brand: {self.brand} | Daily rate: PHP {self.daily_rate:.2f}"


class Car(Vehicle):
    def __init__(self, brand, daily_rate, seat_capacity):
        super().__init__(brand, daily_rate)
        self.seat_capacity = seat_capacity

    def __str__(self):
        return f"Car: {self.brand} | Daily rate: PHP {self.daily_rate:.2f} | Seats: {self.seat_capacity}"


class Motorcycle(Vehicle):
    def __init__(self, brand, daily_rate, engine_capacity_cc):
        super().__init__(brand, daily_rate)
        self.engine_capacity_cc = engine_capacity_cc

    def __str__(self):
        return f"Motorcycle: {self.brand} | Daily rate: PHP {self.daily_rate:.2f} | Engine: {self.engine_capacity_cc} cc"


def main():
    days = 3    
    

    vehicles = [
        Car("Toyota", 1500.00, 5),
        Motorcycle("Honda", 500.00, 150)
    ]

    print()
    
    for vehicle in vehicles:
        print(vehicle)
        charge = vehicle.calculate_rental(days)
        print(f"Rental charge for {days} days: PHP {charge:.2f}")
        
        print()


if __name__ == "__main__":
    main()
