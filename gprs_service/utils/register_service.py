import json
import os
from tornado.httpclient import AsyncHTTPClient


class Service():
    def __init__(self, host, port, ifc, methods):
        self.ifc_id = None
        self.host = host
        self.port = port
        self.ifc = ifc
        self.methods = methods
        self.observer_host = os.environ['OBSERVER_IP']
        self.observer_port = os.environ['OBSERVER_PORT']
        self.http_client = AsyncHTTPClient()

    def get_id(self, response):
        params = json.loads(response.body.decode('utf-8'))
        self.ifc_id = params['id']
        logging.info('Got an id, it is: %s', self.ifc_id)

    def register_interface(self):
        feed = {
            "ip": self.host,
            "port": self.port,
            "name": self.ifc,
            "methods": self.methods
        }
        self.http_client.fetch(
            "http://%s:%s/register" % (self.observer_host, self.observer_port),
            self.get_id, method='POST',
            body=json.dumps(feed),
            headers={'Content-type': 'application/json'}
        )

    def unregister_interface(self):
        if self.ifc_id is None:
            raise Exception('Cannot unregister, interface not registred')
        feed = {
            "id": self.ifc_id
        }
        self.http_client.fetch(
            "http://%s:%s/unregister" % (self.observer_host, self.observer_port),
            self.unreg_result, method='POST',
            body=json.dumps(feed),
            headers={'Content-type': 'application/json'}
        )
