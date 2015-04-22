


START_TIME=1427835600000


for i in {1..50000}; do 

let TIME=START_TIME+i*3600*1000
VAL=$(( 15 + ( RANDOM % 10 )  ))

curl -i -X POST -d "{\"uid\":\"markos\",\"apikey\":\"markos-api-key\",\"value\":\"$VAL\",\"time\":\"$TIME\"}" -H "Accept: application/json" -H "Content-Type: application/json"  http://localhost:8080/hydromon/api/sensor/1/addvalue

done

