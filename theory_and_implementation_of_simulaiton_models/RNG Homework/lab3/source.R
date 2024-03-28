library("simEd")

myArr1<-function(){rexp(1,rate=1)}
myArr2<-function(){rexp(1,rate=11/10)}
mySvc1<-function(){rexp(1,rate=10/9)}

output1<-ssq(maxArrivals=20, seed=8675309, interarrivalFcn = myArr1, serviceFcn = mySvc1, saveAllStats = TRUE, showOutput = FALSE)
output2<-ssq(maxArrivals=20, seed=8675309, interarrivalFcn = myArr2, serviceFcn = mySvc1, saveAllStats = TRUE, showOutput = FALSE)

print("Output1 Arrival Times")
print(output1$interarrivalTimes)
print("Output1 Service Times")
print(output1$serviceTimes)

print("Output2 Arrival Times")
print(output2$interarrivalTimes)
print("Output2 Service Times")
print(output2$serviceTimes)

arrivalTimes1<-cumsum(output1$interarrivalTimes)
completionTimes1<-arrivalTimes1+output1$sojournTimes

arrivalTimes2<-cumsum(output2$interarrivalTimes)
completionTimes2<-arrivalTimes2+output2$sojournTimes

par(mfrow=c(2,1))

indices<-seq_along((output1$numInSystemT[output1$numInSystemT<=5]))
plot(output1$numInSystemT[indices], output1$numInSystemN[indices], type="s", xlim=c(0,5), bty="n", las=1)
abline(v=completionTimes1[4], lty=2)

indices<-seq_along((output2$numInSystemT[output2$numInSystemT<=5]))
plot(output2$numInSystemT[indices], output2$numInSystemN[indices], type="s", xlim=c(0,5), bty="n", las=1)
abline(v=completionTimes2[4], lty=2)

set.seed(8675309)
rexp(1,rate=1)
rexp(1,rate=1)
rexp(1,rate=1)
rexp(1,rate=10/9)
rexp(1,rate=10/9)
rexp(1,rate=10/9)

set.seed(8675309)
rexp(1,rate=1)
rexp(1,rate=10/9)
rexp(1,rate=1)
rexp(1,rate=10/9)
rexp(1,rate=1)
rexp(1,rate=10/9)

set.seed(8675309)
vexp(1,rate=1,stream=1)
vexp(1,rate=1,stream=1)
vexp(1,rate=1,stream=1)
vexp(1,rate=10/9,stream=2)
vexp(1,rate=10/9,stream=2)
vexp(1,rate=10/9,stream=2)

set.seed(8675309)
vexp(1,rate=1,stream=1)
vexp(1,rate=10/9,stream=2)
vexp(1,rate=1,stream=1)
vexp(1,rate=10/9,stream=2)
vexp(1,rate=1,stream=1)
vexp(1,rate=10/9,stream=2)

#with streams
myArr1<-function(){vexp(1,rate=1,stream=1)}
myArr2<-function(){vexp(1,rate=11/10,stream=1)}
mySvc1<-function(){vexp(1,rate=10/9,stream=2)}

output1<-ssq(maxArrivals=20, seed=8675309, interarrivalFcn = myArr1, serviceFcn = mySvc1, saveAllStats = TRUE, showOutput = FALSE)
output2<-ssq(maxArrivals=20, seed=8675309, interarrivalFcn = myArr2, serviceFcn = mySvc1, saveAllStats = TRUE, showOutput = FALSE)

print(sum(output1$interarrivalTimes-output2$interarrivalTimes))
print(sum(output1$serviceTimes-output2$serviceTimes))