# Weather_Aus
CBA_USECASE

Collecting Weather Data with Flume and Spark Streaming

1. Collect Weather data in Python
2. Connect the Python code to Flume
3. Use Spark Streaming to Analyze the data in real time.


# Collect Weather Data in Python

Collect weather data from a third party service in JSON fromat:-
http://api.openweathermap.org/data/2.5/group?id=2078025,2162733,2155472,2174003,2158177,2147714,2063523,2165087,2171707,2171507&units=metric&APPID=be51c93d667ab0b900b063a4e2d96e70


# Using Flume to Transport Data

A source, which picks up data
A channel, which holds data until delivery
And a sink, which delivers data to a receiver -- this could be a file, a streaming agent, or another Flume agent.
These components are defined in a configuration file as follows.

#Use Spark Streaming to Analyze the data in real time.

Spark Streaming application to process that data. Our aim is to get the weather data as pipe delimited with the required fields
