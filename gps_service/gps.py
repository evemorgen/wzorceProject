import os
import logging

from tornado.web import Application
from tornado.ioloop import IOLoop

from utils import logging_config
from utils import Service
from utils import Coords
from handlers import HealthCheckHandler
from handlers import MainHandler
from workers import GpsModuleWorker


PORT = 12345  # replace this with random port when register will be working


def make_app(coords):
    return Application([
        (r'/', MainHandler, {"coords": coords}),
        (r'/healthcheck', HealthCheckHandler),
    ])


if __name__ == '__main__':
    os.environ['GPS_ROOT'] = os.getcwd()
    logging_config()
    logging.info("starting gps app")
    coords = Coords()
    gps_worker = GpsModuleWorker(coords)
    service = Service('0.0.0.0', PORT, 'GPS')
    service.register_interface()
    app = make_app(coords)
    app.listen(PORT)
    IOLoop.current().start()
