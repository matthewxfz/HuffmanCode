@author Fangzhou Xiong
@time 02/12/2016
This is the layout of the file intern:

|
|____Huffman
| |____bin
| | |____edu
| | | |____iit
| | | | |____xfz
| | | | | |____CodeRow.class
| | | | | |____CS401ArrayImpl.class
| | | | | |____CS401PriorityQueue.class
| | | | | |____FileIterator.class
| | | | | |____Huffman.class
| | | | | |____HuffmanCode.class
| | | | | |____HuffmanTree$TreeNode.class
| | | | | |____HuffmanTree.class
| | | | | |____TestClass.class
| | | | | |____Where.class
| |____src
| | |____edu
| | | |____iit
| | | | |____xfz
| | | | | |____CodeRow.java
| | | | | |____CS401ArrayImpl.java
| | | | | |____CS401PriorityQueue.java
| | | | | |____FileIterator.java
| | | | | |____Huffman.java
| | | | | |____HuffmanCode.java
| | | | | |____HuffmanTree.java
| | | | | |____TestClass.java
| | | | | |____Where.java
|____Huffman.jar
|____readme.txt

An already compiled jar file in the ./Huffman.jar

########################
USAGE
#######################

Usage:

java -jar Huffman.jar encode "[encoding text]" <path/output file name> 
java -jar Huffman.jar decode <path/output file name>

———————————————

###########################
[HOW TO RUN THE PROJECTS]
###########################

* To run this project, you should best use JDK 1.7 or above and Junit Library.
* Before running the application in cmd, make sure you have CLASSPATH setted right.

You can run the project with IDE or with out IDE:

[Run the project with IDE]
_______________________________
To run the project with IDE, simply import the project as JAVA_PROJECT.
Before you run the project, please read the homework instruction above, and if you are running project concerning calculating the runtime, please add these argument into the VM argument variants in IDE:

-Djava.compiler=NONE

[Run the project without IDE]
_______________________________

If you do not have IDE. After unzip the compressed file, you can compiler the java file your self (find the <filename>.java according to the layout above) by running the common:
java <javafilename>.java

For files in a package, you should:
1. first get the class filed.
2. Then you should create a MANIFEST.MF File with format like:

Manifest-Version: 1.0  
Created-By: anytime  
Class-Path: .  
Main-Class: edu.iit.xfz.Huffman

3. Put the MANIFEST.MF with the class file and create the jar packet with command:
jar cvfm [packetName].jar MANIFEST.MF -C [path to projects] .  

And when you have the class file, run the command:
java -jar -Djava.compiler=NONE <javafilenameonly>.jar

check the console for result.




