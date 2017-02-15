import serial


class Serial_init:
    def __init__(self):
        self.console = serial.Serial(
            port='/dev/ttyAMA0',
            baudrate=9600,
        )

