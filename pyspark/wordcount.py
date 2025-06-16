from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("WordCount").getOrCreate()

text = spark.read.text("example.txt")
words = text.selectExpr("explode(split(value, ' ')) as word")
wordCounts = words.groupBy("word").count()
wordCounts.show()

spark.stop()

