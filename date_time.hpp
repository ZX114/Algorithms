#ifndef DATETIME_HPP_
#define DATETIME_HPP_

#include <ctime>
#include <cstring>
#include <string>

class DateTime
{
public:
    class TimeDelta {
    public:
        TimeDelta(const double seconds) : seconds_(seconds) {
            minutes_ = seconds_ / 60.0;
            hours_ = minutes_ / 60.0;
            days_  = hours_ / 24.0;
        }

        double seconds() const { return seconds_; }
        double minutes() const { return minutes_; }
        double hours() const { return hours_; }
        double days() const { return days_; }

    private:
        double seconds_;
        double minutes_;
        double hours_;
        double days_;
    };
public:
    static DateTime now() {
        return DateTime();
    }
    static std::string now_string() {
        time_t utc = std::time(nullptr);
        std::tm tm;
        std::memcpy(&tm, std::localtime(&utc), sizeof(tm));
        char str[32] = {0};
        std::strftime(str, sizeof(str), "%Y%m%d%H%M%S", &tm);
        return std::string(str);
    }

    explicit DateTime();
    explicit DateTime(const int year, const int month, const int day);
    DateTime(const DateTime& orig) = default;
    ~DateTime() = default;
    DateTime& operator=(const DateTime& rhs) {
        utc_time_ = rhs.utc_time_;
        local_tm_ = rhs.local_tm_;
        return *this;
    }

    std::string to_string() {
        char str[32] = {0};
        std::strftime(str, sizeof(str), "%Y-%m-%d %H:%M:%S", &local_tm_);
        return std::string(str);
    }

    friend DateTime operator-(const DateTime& dt, const DateTime::TimeDelta& td);
    friend DateTime operator+(const DateTime& dt, const DateTime::TimeDelta& td);
    friend DateTime::TimeDelta operator-(const DateTime& dt1, const DateTime& dt2);
    friend bool operator==(const DateTime& dt1, const DateTime& dt2);
    friend bool operator!=(const DateTime& dt1, const DateTime& dt2);
    friend bool operator<(const DateTime& dt1, const DateTime& dt2);
    friend bool operator>(const DateTime& dt1, const DateTime& dt2);
private:
    explicit DateTime(time_t utc_secs) {
        utc_time_ = utc_secs;
        std::memcpy(&local_tm_, std::localtime(&utc_time_), sizeof(local_tm_));
    }
    time_t utc_time_;
    std::tm local_tm_;
};

DateTime::DateTime()
    : DateTime(std::time(nullptr))
{
}

DateTime::DateTime(const int year, const int month, const int day)
{
    std::tm time;
    time.tm_year = year - 1900;
    time.tm_mon = month - 1;
    time.tm_mday = day;
    time.tm_hour = 0;
    time.tm_isdst = 0;
    time.tm_min = 0;
    time.tm_sec = 0;
    DateTime(std::mktime(&time));
}

DateTime operator-(const DateTime& dt, const DateTime::TimeDelta& td) {
    return DateTime(dt.utc_time_ - static_cast<time_t>(td.seconds()));
}

DateTime operator+(const DateTime& dt, const DateTime::TimeDelta& td) {
    return DateTime(dt.utc_time_ + static_cast<time_t>(td.seconds()));
}

DateTime::TimeDelta operator-(const DateTime& dt1, const DateTime& dt2) {
    return DateTime::TimeDelta(dt1.utc_time_ - dt2.utc_time_);
}

bool operator==(const DateTime& dt1, const DateTime& dt2) {
    return dt1.utc_time_ == dt2.utc_time_;
}

bool operator!=(const DateTime& dt1, const DateTime& dt2) {
    return !(dt1 == dt2);
}

bool operator<(const DateTime& dt1, const DateTime& dt2) {
    DateTime::TimeDelta td = dt1 - dt2;
    return td.seconds() < 0;
}

bool operator>(const DateTime& dt1, const DateTime& dt2) {
    DateTime::TimeDelta td = dt1 - dt2;
    return td.seconds() > 0;
}

#endif
