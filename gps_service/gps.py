import os
import logging

from tornado.web import Application
from tornado.ioloop import IOLoop

from utils import logging_config, Service
from handlers import HealthCheckHandler, MainHandler
from workers import GpsModuleWorker


PORT = 12345  # replace this with random port when register will be working


def make_app():
    return Application([
        (r'/', MainHandler),
        (r'/healthcheck', HealthCheckHandler),
    ])


if __name__ == '__main__':
    os.environ['GPS_ROOT'] = os.getcwd()
    logging_config()
    logging.info("starting gps app")
    gps_worker = GpsModuleWorker()
    service = Service('0.0.0.0', PORT, 'GPS')
    service.register_interface()
    app = make_app()
    app.listen(PORT)
    IOLoop.current().start()
