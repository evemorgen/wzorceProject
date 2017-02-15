import serial


class Serial_init:
    console = serial.Serial(
        port='/dev/ttyAMA0',
        baudrate=9600,
    )

