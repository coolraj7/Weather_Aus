#!/usr/bin/python
import urllib2, time
import sys
while True:
  data=urllib2.urlopen("http://api.openweathermap.org/data/2.5/group?id=2078025,2162733,2155472,2174003,2158177,2147714,2063523,2165087,2171707,2171507&units=metric&APPID=be51c93d667ab0b900b063a4e2d96e70").read()
  print "%s" %data
  sys.stdout.flush()
  time.sleep(8)
~

