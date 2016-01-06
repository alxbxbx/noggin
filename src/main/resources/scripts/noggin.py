import os
from os import listdir
from os.path import isfile, join
import MySQLdb, datetime
from datetime import timedelta


def main():
	#Configuration
	temp_path = 'C:/Lucene/temp'
	host = 'localhost'
	username = 'root'
	password = ''
	database_name = 'noggin'

	#Read all files from temp folder
	all_files = [f for f in listdir(temp_path) if isfile(join(temp_path, f))]

	#Make connection
	db = MySQLdb.connect(host, username, password, database_name)
	cursor = db.cursor()
	statement = 'SELECT * FROM books'

	#Execute sql statement
	cursor.execute(statement)
	results = cursor.fetchall()

	#Delete all files older than 1 day
	for row in results:
		if(int(row[10])==1):
			ms = row[11]
			mat =  datetime.datetime.now() - datetime.datetime.fromtimestamp(long(float(ms))/1000.0)
			if(mat>timedelta(1)):
				file_path = temp_path + "/" + row[2]
				os.remove(file_path)

	#Delete trash files
	count = False
	for f in all_files:
		for row in results:
			if row[2] == str(f):
				count = True
				break
		if count == False:
			file_path = temp_path + "/" + str(f)
			os.remove(file_path)
		count = False

if __name__ == '__main__':
	main()