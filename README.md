
## Test task


### Clone repository:
git clone https://github.com/tarasmal/pumb-test

## **Start the app**
To start the app you have to start start-app.sh script, which is located in the root of this repository \
_<path_to_bash>_ _<path_to_start-app.sh>_ \
## App would be available by this URL: 
http://localhost:8080
## Documentation:
http://localhost:8080/swagger-ui/index.html

# CURL examples
GET 
##
```
    curl -X 'GET' 'http://localhost:8080/animals' -H 'accept: application/json'
```

POST
##
```
curl -X POST http://localhost:8080/files/uploads -H "Content-Type: text/csv" --data-binary @<path_to_animals_csv>
```
```
curl -X POST http://localhost:8080/files/uploads -H "Content-Type: application/xml" --data-binary @<path_to_animals_xml>
```