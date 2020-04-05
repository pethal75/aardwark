Read me file for Paragon Java application.
------------------------------------------

Application is written in plain Java, and serves for parsing text files with store items, and creates paragon for these input files. 
Items that are read from text file are compared with internal store, following items are allowed:

* SIM card
* Phone case
* Wired earphones
* Phone insurance
* Wireless earphones

Other items are skipped.

Standard store items are taxed with 12% tax, except insurance items, that have no tax.

Usage of program is following:
------------------------------

java -cp dist\Paragon.jar com.paragon.ParagonMain <input-file.txt>

For input file (data\paragon1.txt):

SIM card
Phone case
Wired earphones

The output would be: 

Paragon data/paragon1.txt result:
-----------------------------------------------
|Paragon ID:                        1229404427|
|Date:                        5.4.2020 9:38:56|
|                                             |
|Items:                                       |
|                                             |
|SIM card                                20,00|
|  Tax                                    2,40|
|                                             |
|Phone case                              10,00|
|  Tax                                    1,20|
|                                             |
|Wired earphones                         30,00|
|  Tax                                    3,60|
|                                             |
-----------------------------------------------
|Total Net Amount:                        0,00|
|                                             |
|Total Tax:                               0,00|
|                                             |
|Total Amount:                            0,00|
-----------------------------------------------