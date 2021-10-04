import tkinter as tk
from tkinter.constants import CENTER, S

import RPi.GPIO as GPIO # Import Raspberry Pi GPIO library

import mysql.connector
from datetime import datetime

mydb = mysql.connector.connect(
  host="173.28.216.4",
  port="3306",
  user="any",
  password="password",
  database="thermometer_db"
)

mycursor = mydb.cursor()

GPIO.setwarnings(False) # Ignore warning for now
GPIO.setmode(GPIO.BOARD) # Use physical pin numbering
GPIO.setup(10, GPIO.IN, pull_up_down=GPIO.PUD_DOWN) # Button Input
GPIO.setup(12, GPIO.IN, pull_up_down=GPIO.PUD_UP) # Switch Input

# Class to share data between methods
class ThermometerGUI:
    rootWindow = None
    displayLabel = None
    temperature = 0
    count = 0

# Initially builds gui
def initializeGUI():
    #Creates GUI Window
    ThermometerGUI.rootWindow = tk.Tk()
    #COnfigues window to be full screen and Black
    ThermometerGUI.rootWindow.attributes('-fullscreen', True)
    ThermometerGUI.rootWindow.config(bg="black")
    #Creates Display Label
    ThermometerGUI.displayLabel = tk.Label(master=ThermometerGUI.rootWindow, text="", font=("Arial", 50))
    #Configures Label
    ThermometerGUI.displayLabel.config(anchor=CENTER,bg="black",fg="white")
    #Places Label in 
    ThermometerGUI.displayLabel.pack(expand=True)

# Loop that reruns itself every 20 seconds
# Takes care of updating the GUI based off inputs, and sends proper outputs
def programLoop():

    #### IF FLIP SWITCHED START ####
    if (switchOn()):
        # Reads in tempature from text document buffer
        file = open("test.txt")
        ThermometerGUI.temperature = file.readline()
        file.close()

        # 1) Send temp to database
        # EVery second, send tempature out
        if (ThermometerGUI.count == 50):
            #
            now = datetime.now()
            if(ThermometerGUI.temperature.strip() == "NULL"):
                sql = now.strftime(f"INSERT INTO temperature (temp, date, probe) VALUES (NULL,'%Y-%m-%d %H:%M:%S',1);")
            else:
                sql = now.strftime(f"INSERT INTO temperature (temp, date, probe) VALUES ({ThermometerGUI.temperature},'%Y-%m-%d %H:%M:%S',1);")
                
            mycursor.execute(sql)
            mydb.commit()

            ThermometerGUI.count = 0
        else:
            ThermometerGUI.count += 1
        # 2) Read computer_buttonPressed() from database
        
        # If the tempatre prode is dis
        if(ThermometerGUI.temperature.strip() == "NULL"):
            ThermometerGUI.displayLabel.configure(text="Probe is disconnected")
        # If button is pressed, display tempature
        elif(buttonPressed() or computer_buttonPressed()): # or computer_buttonPressed()
            ThermometerGUI.displayLabel.configure(text=ThermometerGUI.temperature + " C")
        # Display nothing
        else:
            ThermometerGUI.displayLabel.configure(text="")
    #### IF FLIP SWITCHED END ####
    else:
        ThermometerGUI.displayLabel.configure(text="")
        if (ThermometerGUI.count == 50):
            #
            now = datetime.now()
            sql = now.strftime(f"INSERT INTO temperature (temp, date, probe) VALUES (NULL,'%Y-%m-%d %H:%M:%S',0);")
                
            mycursor.execute(sql)
            mydb.commit()

            ThermometerGUI.count = 0
        else:
            ThermometerGUI.count += 1
    # Repeats program
    ThermometerGUI.rootWindow.after(20,programLoop)

#Starts the GUI
def startGUI():
    ThermometerGUI.rootWindow.after(20,programLoop)
    ThermometerGUI.rootWindow.mainloop()

#Gets when putton is pressed
def buttonPressed():
    return GPIO.input(10) == GPIO.HIGH

#Gets when switch is on
def switchOn():
    return GPIO.input(12) == GPIO.LOW

def computer_buttonPressed():
    try:
        mycursor.execute("SELECT screen_bool FROM button;")
        myresult = mycursor.fetchall()
        return myresult[-1][0] == "TRUE"
    except:
        return False
    


initializeGUI()
startGUI()