Currently avaliable endpoints are:
  1. /healthcheck
  2. /real_data

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
curl -X POST -d '{}' http://host_ip:host_port/healthcheck
```
Return:
```
{
    "number": 7,
    "status": "OK"
}
```

----------


###1. real_data
    Real_data endpoint provides interface to save input from real tram courses.
    You can choice whether you like to use GET or POST method
<table>
    <tr><td>Method: GET</td></tr>
    <tr><td>Format: GET formatted parameters<br />
    <tr><td>Parameters: <br /><br />
        <ol>
            <li>id - integer - id of survey</li>
            <li>line - integer - id of tram line</li>
            <li>lat - float - actual tram latitude</li>
            <li>lon - float - actural tram longitude</li>
            <li>ts - integer - unix style timestamp</li>
        </ol>
        </tr></td>
    <tr><td>Returns: "OK"<br /><br />
</table>

Usage example:

Call:
```
curl -X GET http://0.0.0.0:8888/real_data?lat=51.00001&lon=19.00007&line=19&ts=1487115546&id=3
```
Return:
```
"OK"
```

<br />

<table>
    <tr><td>Method: POST</td></tr>
    <tr><td>Format: JSON object<br />
    <tr><td>Fields: <br /><br />
        <ol>
            <li>id - integer - id of survey</li>
            <li>line - integer - id of tram line</li>
            <li>lat - float - actual tram latitude</li>
            <li>lon - float - actural tram longitude</li>
            <li>ts - integer - unix style timestamp</li>
        </ol>
        </tr></td>
    <tr><td>Returns: "OK"<br /><br />
</table>

Usage example:

Call:
```
curl -X POST http://0.0.0.0:8888/real_data -d '{"id": 3, "line": 19, "lat":50.00001, "lon":19.00007, "ts":1487115546}'
```
Return:
```
"OK"
```

----------
