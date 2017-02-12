import logging
import pynmea2


class Coords():

    def __init__(self):
        self.frames_list = []
        self.state_dict = {
            'status': None
        }

    def _find_fields(self, frame):
        if hasattr(frame, 'latitude'):
            self.state_dict['pos']['latitude'] = frame.latitude
        if hasattr(frame, 'latitude_minutes'):
            self.state_dict['pos']['latitude_minutes'] = frame.latitude_minutes
        if hasattr(frame, 'latitude_seconds'):
            self.state_dict['pos']['latitude_seconds'] = frame.latitude_seconds
        if hasattr(frame, 'latitude'):
            self.state_dict['pos']['longitude'] = frame.longitude
        if hasattr(frame, 'latitude_minutes'):
            self.state_dict['pos']['longitude_minutes'] = frame.longitude_minutes
        if hasattr(frame, 'latitude_seconds'):
            self.state_dict['pos']['longitude_seconds'] = frame.longitude_seconds
        if hasattr(frame, 'num_sv_in_view'):
            self.state_dict['sat']['sat_in_view'] = frame.num_sv_in_view
        if hasattr(frame, 'num_sats'):
            self.state_dict['sat']['sat_used'] = frame.num_sats
        if hasattr(frame, 'timestamp'):
            self.state_dict['timestamp'] = frame.timestamp

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
