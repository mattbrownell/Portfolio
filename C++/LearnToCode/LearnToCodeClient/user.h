#ifndef USER_H
#define USER_H

#include <QString>
#include <vector>
#include <QVector>
using namespace std;
class User
{
public:
    User(QString _username, bool _isInstructor);

    User(QString _username, QVector<int> _pointsPerLevel, int _totalPoints, bool _isInstructor);

    QString username;
    QString password;
    QVector<int> pointsPerLevel; // Store total points per each of the 9 levels
    int totalPoints;
    bool isInstructor;
    bool isConnected = true; // changes to false if user logs in with "offline mode"


};

#endif // USER_H
