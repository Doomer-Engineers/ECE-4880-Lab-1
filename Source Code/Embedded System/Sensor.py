import time
from w1thermsensor import W1ThermSensor
from w1thermsensor import SensorNotReadyError
from w1thermsensor import NoSensorFoundError

# Thermometer sensor object
initSensor = False
while(not initSensor):
    try:
        sensor = W1ThermSensor()
        initSensor  = True
    except NoSensorFoundError:
        file = open("test.txt",'w')
        file.write("NULL")
        file.close()

# Repeatidly gets sensor data and sends it to database
def getTemp():
    while True:
        try:
            temperature = sensor.get_temperature()

            file = open("test.txt",'w')
            file.write("{:.2f}\n".format(temperature))
            file.close()
        except:# SensorNotReadyError:
            time.sleep(1)
            try:
                temperature = sensor.get_temperature()

                file = open("test.txt",'w')
                file.write("{:.2f}\n".format(temperature))
                file.close()
            except:# SensorNotReadyError:
                time.sleep(1)
                try:
                    temperature = sensor.get_temperature()

                    file = open("test.txt",'w')
                    file.write("{:.2f}\n".format(temperature))
                    file.close()
                except:# SensorNotReadyError:
                    file = open("test.txt",'w')
                    file.write("NULL")
                    file.close()
        time.sleep(1)

getTemp()
