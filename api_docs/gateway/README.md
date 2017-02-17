# Gateway

Gateway is a application that links client application with avaliable microservices in our system. This app acts as sort of "proxy server" for communication between client app and our services. This involves ip-port-less communication with specific services.

### Service side api
Gateway provides api to:
  - register service - when it is ready to work
  - unregister service - when it is ending its lifetime


1. register
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        <ul>
            <li> ip - string - ip of host where service is running </li>
            <li> port - int - port which service is running on </li>
            <li> name - string - name of service </li>
            <li> methods - list of strings - list of avaliable methods
    </ul>
  </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>id - string - string which identifies service</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/register -d '{"ip": "192.168.1.123", "port": 9999, "name": "GPS", "methods": ["get_pos", "get_time"]}'
```
Return:
```
{
    "id": "68255b9d987c2885ff0ddbc92dbb60cb"
}
```

----------

2. unregister
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        <ul>
            <li> id - string - id of service given within registration </li>
    </ul>
  </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>result - string - OK/ERROR</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/unregister -d '{"id": "68255b9d987c2885ff0ddbc92dbb60cb"}'
```
Return:
```
{
    "result": "OK"
}
```

----------

### Client side api
Gateway provides access to services api without knowledge about their location.

api methods are:
  - show - lists avaliable services
  - methods - lists avaliable methods for specific service
  - request - make request to service

1. show
<table>
    <tr><td>Method: GET</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        Null
  </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>services - list of strings - list of avaliable services names</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X GET 192.168.1.123:12345/show -d '{}'
```
Return:
```
{
    "services": ["GPS", "TEST"]
}
```

----------

2. methods
<table>
    <tr><td>Method: GET</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        <ol>
            <li> name - string - name of service which methods you want
        </ol>
  </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>methods - list of strings - list of service avaliable methods</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X GET 192.168.1.123:12345/methods?name=GPS
```
Return:
```
{
    "methods": ["get_sat_info", "get_pos_info", "get_all_data", "get_raw_frames"]
}
```

----------

3. request
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        <ol>
            <li> service - string - name of service </li>
            <li> method - string - name of method you want to use </li>
            <li> data - json object - method arguments </li>
        </ol>
  </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>result - json object - data returned from service</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/request -d '{"service": "GPS", "method": "get_raw_frames", "data": {"n": 10}}'
```
Return:
```
{
    "result": [
    "$GPGGA,,,,,,0,00,99.99,,,,,,*48",
    "$GPGSA,A,1,,,,,,,,,,,,,99.99,99.99,99.99*30",
    "$GPGSV,2,1,05,01,,,18,03,,,22,27,,,22,28,,,20*79",
    "$GPGSV,2,2,05,30,,,21*7C", "$GPGLL,,,,,,V,N*64",
    "$GPVTG,,,,,,,,,N*30", "$GPGGA,,,,,,0,00,99.99,,,,,,*48",
    "$GPGSA,A,1,,,,,,,,,,,,,99.99,99.99,99.99*30",
    "$GPGSV,1,1,04,01,,,15,27,,,23,28,,,21,30,,,21*75",
    "$GPGLL,,,,,,V,N*64"
]
}
```
For gps api reference please visit https://github.com/evemorgen/wzorceProject/tree/master/api_docs/gps

----------

