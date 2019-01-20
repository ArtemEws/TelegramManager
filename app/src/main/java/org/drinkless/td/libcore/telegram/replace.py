import os

# Replaces package names in all files except Authorization.java

files = os.listdir(path='apihelper')

for file in files:

	if file[:6] == 'Author':
		continue

	fin = open('apihelper/'+file, 'r')
	s = fin.read()
	s = s.replace('org.drinkless.tdlib', 'org.drinkless.td.libcore.telegram')
	fin.close()
	fout = open('apihelper/'+file, 'w')
	fout.write(s)
	fout.close()