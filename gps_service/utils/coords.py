import logging
import pynmea2


class Coords():

    def __init__(self):
        self.frames_list = []

    def add_frame(self, frame):
        try:
            parsed_frame = pynmea2.parse(frame)
            self.frames_list.append(parsed_frame)
            self.frames_list = self.frames_list[-100:]
        except pynmea2.ParseError:
            logging.info('parse error :(')
        except pynmea2.nmea.ChecksumError:
            logging.info('checksum does not match, discarding')

    def get_last_n_frames(self, n):
        return self.frames_list[-n:]
