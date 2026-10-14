import sys

message = input("Enter a message: ")
bytes_object = message.encode('utf-8')
print(1, bytes_object)

string_object = bytes_object.decode('utf-8')
print(2, string_object)

hex_string = bytes_object.hex()
print(3, hex_string)

bytes_object = bytes.fromhex(hex_string)
print(4, bytes_object)

string_object = message
bytearray_object = bytearray(string_object, 'utf-8')
print(5, bytearray_object)

bytearray_object = bytearray(bytes_object)
print(6, bytearray_object)

sliced_bytes = bytes_object[6:]
print(7, sliced_bytes)

bytearray_object = bytearray(bytes_object)
if len(bytearray_object) > 0:
    first_char_lower = chr(bytearray_object[0]).lower()
    bytearray_object[0] = ord(first_char_lower)
result_string = bytearray_object.decode('utf-8')
print(8, result_string)
