service_times_model1 <- scan("ssq1.txt")
service_times_model2 <- scan("ssq2.txt")

# Find the range of the combined data
min_value <- min(c(service_times_model1, service_times_model2))
max_value <- max(c(service_times_model1, service_times_model2))

# Define breaks
breaks <- seq(min_value, max_value, by=0.5)

# Create histograms
hist1 <- hist(service_times_model1, plot=FALSE, breaks=)
hist2 <- hist(service_times_model2, plot=FALSE, breaks=breaks)

# Plot histograms
par(mfrow=c(1, 2))
plot(hist1, col="blue", xlim=c(min_value, max_value), main="Service Model 1", xlab="Service Time", ylab="Frequency")
plot(hist2, col="red", xlim=c(min_value, max_value), main="Service Model 2", xlab="Service Time", ylab="Frequency")
