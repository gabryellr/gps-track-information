from urllib.request import urlopen
from io import BytesIO
from zipfile import ZipFile
import glob, os
import shutil

print("Downloading file...")
http_response = urlopen("https://codechallengestracc.blob.core.windows.net/code-challenge/dublin-dataset.zip")
zipfile = ZipFile(BytesIO(http_response.read()))
print("Unzipping file...")
zipfile.extractall(path='.')

csvCounter = len(glob.glob1(".","*.csv"))

print("Deleting files that will not used...")
for file in glob.glob("*.csv"):
    if csvCounter > 1:
        os.remove(file)
        csvCounter-=1
    elif csvCounter == 1:
        print("Renaming file name to vehicles.csv")
        fileName_absolute = os.path.basename(file)
        os.rename(fileName_absolute, "vehicles.csv")
        print("Moving file to src/main/resources/")
        shutil.move("./vehicles.csv", "src/main/resources/vehicles.csv")

print("Files was deleted with succcess and file was moved!")


