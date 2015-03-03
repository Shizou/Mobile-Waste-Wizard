pw = []
ewaste = []
green = []
grey = []
hhw = []
blue = []
bttswd = []
yw = []
sm = []
ow = []
total = []

fo = open("pw.txt", "r").read()
for line in fo.split("\n"):
    pw.append(line.lower())
    total.append(line.lower())

fo = open("ewaste.txt", "r").read()
for line in fo.split("\n"):
    ewaste.append(line.lower())
    total.append(line.lower())

fo = open("green.txt", "r").read()
for line in fo.split("\n"):
    green.append(line.lower())
    total.append(line.lower())

fo = open("grey.txt", "r").read()
for line in fo.split("\n"):
    grey.append(line.lower())
    total.append(line.lower())

fo = open("hhw.txt", "r").read()
for line in fo.split("\n"):
    hhw.append(line.lower())
    total.append(line.lower())

fo = open("blue.txt", "r").read()
for line in fo.split("\n"):
    blue.append(line.lower())
    total.append(line.lower())

fo = open("BTTSWD.txt", "r").read()
for line in fo.split("\n"):
    bttswd.append(line.lower())
    total.append(line.lower())

fo = open("yw.txt", "r").read()
for line in fo.split("\n"):
    yw.append(line.lower())
    total.append(line.lower())

fo = open("sm.txt", "r").read()
for line in fo.split("\n"):
    sm.append(line.lower())
    total.append(line.lower())

fo = open("ow.txt", "r").read()
for line in fo.split("\n"):
    ow.append(line.lower())
    total.append(line.lower())

while(1):
    item =input("What would you like to throw away?")
    s = ""
    
    if item in pw:
        print("Prohibited Waste")
        s = "Prohibited Waste"
    elif item in ewaste:
        print("E-Waste")
        s = "E-Waste"
    elif item in green:
        print("Green Bin")
        s = "Green Bin"
    elif item in grey:
        print("Grey Bin - Garbage")
        s = "Grey Bin - Garbage"
    elif item in hhw:
        print("Household Hazardous Waste")
        s = "Household Hazardous Waste"
    elif item in blue:
        print("Blue Bin")
        s =  "Blue Bin"    
    elif item in bttswd:
        print("Bring to Transfer Station/Waste Depot")
        s = "Bring to Transfer Station/Waste Depot"
    elif item in yw:
        print("Yard Waste")
        s = "Yard Waste"
    elif item in sm:
        print("Scrap Metal")
        s = "Scrap Metal"
    elif item in ow:
        print("Oversized Waste")
        s = "Oversized Waste" 
    else:
        cnt = 0;
        print("Would you like to try:")
        for x in range (0, len(total)):            
            if item in total[x]:
                cnt = cnt + 1
                s = s + total[x] + "\n"
                print(total[x])
            if cnt == 4:
                break
        """max1 = [-1,-1,-1,-1]
        max2 = ["","","",""]
        for x in range (0, len(total)):
            cnt = 0
            for y in range(0,len(total[x])):
                if("""
    fo = open("Bin.txt", "w");
    fo.write(s);
    fo.close();
