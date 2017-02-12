Currently avaliable endpoints are:
  1. /healthcheck
  2. /


----------


###1. Healthcheck
    Healthcheck is an endpoint which provides only one method to check if your application did not crashed.
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method does not requires any parameters, you need to pass an empty object. - {}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>status - current backend state, normally should return "OK" string </li>
            <li>number - random number between 0 and 9 </li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST -d '{}' http://localhost:12345/
```
Return:
```
{
    "number": 7,
    "status": "OK"
}
```

----------


###2. Root endpoint /
    This endpoint provides API to get all necessary data about gps position/statistics.
    You can choice which data to pull by specifying "method" in json object.
    Avaliable methods are:
        - get_sat_info
        - get_pos_info
        - get_all_data
        - get_raw_frames

# 1. get_sat_info
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method does not requires any parameters, you need to pass an object with method. - {"method": "get_sat_info"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>sat_in_view - current number of satelites in range</li>
            <li>sat_used - current number of satelites fixed</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/ -d '{"method": "get_sat_info"}'
```
Return:
```
{
    "sat_in_view": "06",
    "sat_used": "00"
}
```

----------

# 2. get_pos_info
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method does not requires any parameters, you need to pass an object with method. - {"method": "get_pos_info"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>latitude</li>
            <li>latitude_minutes</li>
            <li>latitude_seconds</li>
            <li>longitude</li>
            <li>longitude_minutes</li>
            <li>longitude_seconds</li>
        </ul>
</table>

Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/ -d '{"method": "get_pos_info"}'
```
Return:
```
{
    "latitude_seconds": 0.0,
    "longitude_seconds": 0.0,
    "longitude": 0.0,
    "longitude_minutes": 0.0,
    "latitude": 0.0,
    "latitude_minutes": 0.0
}
```

INFO - when 0.0 occurs in all variables that means, gps is not fixed yet
----------

# 3. get_all_data
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        This method does not requires any parameters, you need to pass an object with method. - {"method": "get_all_data"}
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li>latitude</li>
            <li>latitude_minutes</li>
            <li>latitude_seconds</li>
            <li>longitude</li>
            <li>longitude_minutes</li>
            <li>longitude_seconds</li>
            <li>sat_in_view</li>
            <li>sat_used</li>
            <li>timestamp - timestamp for last received frame from gps in format HH:MM:SS</li>
        </ul>
</table>
Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/ -d '{"method": "get_all_data"}'
```
Return:
```
{
    "sat": {
        "sat_in_view": "06",
        "sat_used": "00"
    },
    "pos": {
        "latitude_seconds": 0.0,
        "longitude_seconds": 0.0,
        "longitude": 0.0,
        "longitude_minutes": 0.0,
        "latitude": 0.0,
        "latitude_minutes": 0.0
    },
    "timestamp": "23:14:25"
}
```

INFO - when 0.0 occurs in all variables that means, gps is not fixed yet
----------


# 4. get_raw_frames
<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON</td></tr>
    <tr><td>Parameters: <br /><br />
        "method": "get_raw_frames",
        "n": integer > 0
        </tr></td>
    <tr><td>Returns:     <br /><br />
    Json object with: <br />
        <ul>
            <li> List of strings of raw frames from GPS </li>
        </ul>
</table>
Usage example:

Call:
```
curl -X POST 192.168.1.123:12345/ -d '{"method": "get_raw_frames", "n": 10}'
```
Return:
```
[
    "$GPGGA,,,,,,0,00,99.99,,,,,,*48",
    "$GPGSA,A,1,,,,,,,,,,,,,99.99,99.99,99.99*30",
    "$GPGSV,2,1,05,01,,,18,03,,,22,27,,,22,28,,,20*79",
    "$GPGSV,2,2,05,30,,,21*7C", "$GPGLL,,,,,,V,N*64",
    "$GPVTG,,,,,,,,,N*30", "$GPGGA,,,,,,0,00,99.99,,,,,,*48",
    "$GPGSA,A,1,,,,,,,,,,,,,99.99,99.99,99.99*30",
    "$GPGSV,1,1,04,01,,,15,27,,,23,28,,,21,30,,,21*75",
    "$GPGLL,,,,,,V,N*64"
]
```

----------

