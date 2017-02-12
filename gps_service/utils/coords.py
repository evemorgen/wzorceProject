import logging
import pynmea2


class Coords():

    def __init__(self):
        self.frames_list = []
        self.state_dict = {
            'status': None
        }

    def _find_fields(self, frame):
        if frame.latitude is not None:
            self.state_dict['latitude'] = frame.latitude
        if frame.latitude is not None:
            self.state_dict['latitude_minutes'] = frame.latitude_minutes
        if frame.latitude_seconds is not None:
            self.state_dict['latitude_seconds'] = frame.latitude_seconds
        if frame.latitude is not None:
            self.state_dict['longitude'] = frame.longitude
        if frame.latitude is not None:
            self.state_dict['longitude_minutes'] = frame.longitude_minutes
        if frame.latitude_seconds is not None:
            self.state_dict['longitude_seconds'] = frame.longitude_seconds


    def add_frame(self, frame):
        try:
            parsed_frame = pynmea2.parse(frame)
            print(dir(parsed_frame))
            self._find_fields(parsed_frame)
            self.frames_list.append(parsed_frame)
            self.frames_list = self.frames_list[-100:]
        except pynmea2.ParseError:
            logging.info('parse error :(')
        except pynmea2.nmea.ChecksumError:
            logging.info('checksum does not match, discarding')

    def get_last_n_frames(self, n):
        return self.frames_list[-n:]
