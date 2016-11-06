/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.5.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QFrame>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QFrame *loginFrame;
    QLabel *loginFailedLabel;
    QPushButton *createLoginButton;
    QLabel *createLoginLabel;
    QWidget *layoutWidget;
    QGridLayout *createLayout;
    QLineEdit *lastNameField;
    QLabel *instructorLabel;
    QLabel *instructorNameLabel;
    QLabel *lastNameLabel;
    QLineEdit *instructorNameField;
    QHBoxLayout *yesNoLayout;
    QCheckBox *yesBox;
    QCheckBox *noBox;
    QLineEdit *firstNameField;
    QLabel *firstNameLabel;
    QPushButton *signUpFinalButton;
    QPushButton *cancelButton;
    QPushButton *loginOffline;
    QLabel *loginOfflineLabel;
    QWidget *gridLayoutWidget;
    QGridLayout *loginLayout;
    QLineEdit *usernameField;
    QLineEdit *passwordField;
    QLabel *passwordLabel;
    QLabel *usernameLabel;
    QPushButton *loginButton;
    QPushButton *splashScreen;
    QFrame *commandFrame;
    QWidget *horizontalLayoutWidget;
    QHBoxLayout *commandLayout;
    QPushButton *forwardButton;
    QPushButton *jumpButton;
    QPushButton *waitButton;
    QPushButton *ifButton;
    QPushButton *loopButton;
    QPushButton *removeButton;
    QLabel *commandsLabel;
    QLabel *loopLabel;
    QPushButton *if1;
    QPushButton *if2;
    QPushButton *loop1;
    QPushButton *loop2;
    QPushButton *loop3;
    QSpinBox *loopSpinBox;
    QPushButton *restartButton;
    QPushButton *if12;
    QPushButton *if22;
    QFrame *line;
    QLabel *loopLabel_2;
    QLabel *loopLabel_3;
    QPushButton *logoutButton;
    QFrame *levelFrame;
    QWidget *gridLayoutWidget_2;
    QGridLayout *levelLayout;
    QVBoxLayout *level3Layout;
    QLabel *level3Label;
    QPushButton *level3Button;
    QVBoxLayout *level7Layout;
    QLabel *level7Label;
    QPushButton *level7Button;
    QVBoxLayout *level2Layout;
    QLabel *level2Label;
    QPushButton *level2Button;
    QVBoxLayout *level4Layout;
    QLabel *level4Label;
    QPushButton *level4Button;
    QVBoxLayout *level1Layout;
    QLabel *level1Label;
    QPushButton *level1Button;
    QVBoxLayout *level5Layout;
    QLabel *level5Label;
    QPushButton *level5Button;
    QVBoxLayout *level6Layout;
    QLabel *level6Label;
    QPushButton *level6Button;
    QVBoxLayout *level9Layout;
    QLabel *level9Label;
    QPushButton *level9Button;
    QVBoxLayout *level8Layout;
    QLabel *level8Label;
    QPushButton *level8Button;
    QLabel *userStatsLabel;
    QFrame *sequenceFrame;
    QWidget *verticalLayoutWidget;
    QVBoxLayout *sequenceLayout;
    QPushButton *runButton;
    QLabel *sequenceLabel;
    QFrame *scoreFrame;
    QFrame *scoreGrid;
    QWidget *gridLayoutWidget_3;
    QGridLayout *scoreLayout;
    QLabel *scoreValue;
    QLabel *highschoolValue;
    QPushButton *continueButton;
    QLabel *highscoreLabel;
    QLabel *scoreLabel;
    QPushButton *homeButton;
    QPushButton *studentStats;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(1117, 628);
        MainWindow->setStyleSheet(QStringLiteral(""));
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        loginFrame = new QFrame(centralWidget);
        loginFrame->setObjectName(QStringLiteral("loginFrame"));
        loginFrame->setGeometry(QRect(50, 50, 901, 451));
        loginFrame->setStyleSheet(QStringLiteral(""));
        loginFrame->setFrameShape(QFrame::StyledPanel);
        loginFrame->setFrameShadow(QFrame::Raised);
        loginFailedLabel = new QLabel(loginFrame);
        loginFailedLabel->setObjectName(QStringLiteral("loginFailedLabel"));
        loginFailedLabel->setGeometry(QRect(30, 320, 181, 20));
        QFont font;
        font.setBold(true);
        font.setWeight(75);
        loginFailedLabel->setFont(font);
        loginFailedLabel->setStyleSheet(QStringLiteral("color: rgb(243, 0, 0); qproperty-alignment: AlignCenter;"));
        createLoginButton = new QPushButton(loginFrame);
        createLoginButton->setObjectName(QStringLiteral("createLoginButton"));
        createLoginButton->setGeometry(QRect(30, 370, 75, 23));
        createLoginLabel = new QLabel(loginFrame);
        createLoginLabel->setObjectName(QStringLiteral("createLoginLabel"));
        createLoginLabel->setGeometry(QRect(10, 350, 121, 16));
        createLoginLabel->setFont(font);
        createLoginLabel->setStyleSheet(QStringLiteral("color: white;"));
        layoutWidget = new QWidget(loginFrame);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(680, 250, 172, 111));
        createLayout = new QGridLayout(layoutWidget);
        createLayout->setSpacing(6);
        createLayout->setContentsMargins(11, 11, 11, 11);
        createLayout->setObjectName(QStringLiteral("createLayout"));
        createLayout->setVerticalSpacing(6);
        createLayout->setContentsMargins(0, 0, 0, 0);
        lastNameField = new QLineEdit(layoutWidget);
        lastNameField->setObjectName(QStringLiteral("lastNameField"));
        lastNameField->setEchoMode(QLineEdit::Normal);

        createLayout->addWidget(lastNameField, 1, 1, 1, 1);

        instructorLabel = new QLabel(layoutWidget);
        instructorLabel->setObjectName(QStringLiteral("instructorLabel"));
        instructorLabel->setFont(font);
        instructorLabel->setStyleSheet(QStringLiteral("color: white;"));

        createLayout->addWidget(instructorLabel, 2, 0, 1, 1);

        instructorNameLabel = new QLabel(layoutWidget);
        instructorNameLabel->setObjectName(QStringLiteral("instructorNameLabel"));
        instructorNameLabel->setFont(font);
        instructorNameLabel->setStyleSheet(QStringLiteral("color: white;"));

        createLayout->addWidget(instructorNameLabel, 3, 0, 1, 1);

        lastNameLabel = new QLabel(layoutWidget);
        lastNameLabel->setObjectName(QStringLiteral("lastNameLabel"));
        lastNameLabel->setFont(font);
        lastNameLabel->setStyleSheet(QStringLiteral("color: white;"));

        createLayout->addWidget(lastNameLabel, 1, 0, 1, 1);

        instructorNameField = new QLineEdit(layoutWidget);
        instructorNameField->setObjectName(QStringLiteral("instructorNameField"));
        instructorNameField->setEchoMode(QLineEdit::Normal);

        createLayout->addWidget(instructorNameField, 3, 1, 1, 1);

        yesNoLayout = new QHBoxLayout();
        yesNoLayout->setSpacing(6);
        yesNoLayout->setObjectName(QStringLiteral("yesNoLayout"));
        yesBox = new QCheckBox(layoutWidget);
        yesBox->setObjectName(QStringLiteral("yesBox"));
        yesBox->setFont(font);
        yesBox->setStyleSheet(QStringLiteral("color: white;"));

        yesNoLayout->addWidget(yesBox);

        noBox = new QCheckBox(layoutWidget);
        noBox->setObjectName(QStringLiteral("noBox"));
        noBox->setFont(font);
        noBox->setStyleSheet(QStringLiteral("color: white;"));

        yesNoLayout->addWidget(noBox);


        createLayout->addLayout(yesNoLayout, 2, 1, 1, 1);

        firstNameField = new QLineEdit(layoutWidget);
        firstNameField->setObjectName(QStringLiteral("firstNameField"));

        createLayout->addWidget(firstNameField, 0, 1, 1, 1);

        firstNameLabel = new QLabel(layoutWidget);
        firstNameLabel->setObjectName(QStringLiteral("firstNameLabel"));
        firstNameLabel->setFont(font);
        firstNameLabel->setStyleSheet(QStringLiteral("color: white;"));

        createLayout->addWidget(firstNameLabel, 0, 0, 1, 1);

        signUpFinalButton = new QPushButton(loginFrame);
        signUpFinalButton->setObjectName(QStringLiteral("signUpFinalButton"));
        signUpFinalButton->setGeometry(QRect(770, 370, 61, 23));
        cancelButton = new QPushButton(loginFrame);
        cancelButton->setObjectName(QStringLiteral("cancelButton"));
        cancelButton->setGeometry(QRect(700, 370, 61, 23));
        loginOffline = new QPushButton(loginFrame);
        loginOffline->setObjectName(QStringLiteral("loginOffline"));
        loginOffline->setGeometry(QRect(140, 370, 75, 23));
        loginOfflineLabel = new QLabel(loginFrame);
        loginOfflineLabel->setObjectName(QStringLiteral("loginOfflineLabel"));
        loginOfflineLabel->setGeometry(QRect(140, 350, 81, 16));
        loginOfflineLabel->setFont(font);
        loginOfflineLabel->setStyleSheet(QStringLiteral("color: white;"));
        loginOfflineLabel->setAlignment(Qt::AlignCenter);
        gridLayoutWidget = new QWidget(loginFrame);
        gridLayoutWidget->setObjectName(QStringLiteral("gridLayoutWidget"));
        gridLayoutWidget->setGeometry(QRect(30, 240, 181, 81));
        loginLayout = new QGridLayout(gridLayoutWidget);
        loginLayout->setSpacing(6);
        loginLayout->setContentsMargins(11, 11, 11, 11);
        loginLayout->setObjectName(QStringLiteral("loginLayout"));
        loginLayout->setContentsMargins(0, 0, 0, 0);
        usernameField = new QLineEdit(gridLayoutWidget);
        usernameField->setObjectName(QStringLiteral("usernameField"));

        loginLayout->addWidget(usernameField, 0, 1, 1, 1);

        passwordField = new QLineEdit(gridLayoutWidget);
        passwordField->setObjectName(QStringLiteral("passwordField"));
        passwordField->setEchoMode(QLineEdit::Password);

        loginLayout->addWidget(passwordField, 1, 1, 1, 1);

        passwordLabel = new QLabel(gridLayoutWidget);
        passwordLabel->setObjectName(QStringLiteral("passwordLabel"));
        passwordLabel->setFont(font);
        passwordLabel->setStyleSheet(QStringLiteral("color: white;"));

        loginLayout->addWidget(passwordLabel, 1, 0, 1, 1);

        usernameLabel = new QLabel(gridLayoutWidget);
        usernameLabel->setObjectName(QStringLiteral("usernameLabel"));
        usernameLabel->setFont(font);
        usernameLabel->setStyleSheet(QStringLiteral("color: white;"));

        loginLayout->addWidget(usernameLabel, 0, 0, 1, 1);

        loginButton = new QPushButton(gridLayoutWidget);
        loginButton->setObjectName(QStringLiteral("loginButton"));
        loginButton->setEnabled(true);

        loginLayout->addWidget(loginButton, 2, 1, 1, 1);

        splashScreen = new QPushButton(loginFrame);
        splashScreen->setObjectName(QStringLiteral("splashScreen"));
        splashScreen->setGeometry(QRect(0, 0, 901, 451));
        QIcon icon;
        icon.addFile(QStringLiteral(":/icons/icons/SplashScreen.png"), QSize(), QIcon::Normal, QIcon::Off);
        splashScreen->setIcon(icon);
        splashScreen->setIconSize(QSize(900, 450));
        splashScreen->setFlat(true);
        splashScreen->raise();
        loginFailedLabel->raise();
        layoutWidget->raise();
        signUpFinalButton->raise();
        loginOffline->raise();
        loginOfflineLabel->raise();
        gridLayoutWidget->raise();
        cancelButton->raise();
        createLoginButton->raise();
        createLoginLabel->raise();
        commandFrame = new QFrame(centralWidget);
        commandFrame->setObjectName(QStringLiteral("commandFrame"));
        commandFrame->setEnabled(true);
        commandFrame->setGeometry(QRect(50, 510, 941, 61));
        commandFrame->setStyleSheet(QStringLiteral(""));
        commandFrame->setFrameShape(QFrame::StyledPanel);
        commandFrame->setFrameShadow(QFrame::Raised);
        horizontalLayoutWidget = new QWidget(commandFrame);
        horizontalLayoutWidget->setObjectName(QStringLiteral("horizontalLayoutWidget"));
        horizontalLayoutWidget->setGeometry(QRect(0, 10, 521, 51));
        commandLayout = new QHBoxLayout(horizontalLayoutWidget);
        commandLayout->setSpacing(6);
        commandLayout->setContentsMargins(11, 11, 11, 11);
        commandLayout->setObjectName(QStringLiteral("commandLayout"));
        commandLayout->setContentsMargins(0, 0, 0, 0);
        forwardButton = new QPushButton(horizontalLayoutWidget);
        forwardButton->setObjectName(QStringLiteral("forwardButton"));
        QIcon icon1;
        icon1.addFile(QStringLiteral(":/icons/icons/forwardIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        forwardButton->setIcon(icon1);
        forwardButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(forwardButton);

        jumpButton = new QPushButton(horizontalLayoutWidget);
        jumpButton->setObjectName(QStringLiteral("jumpButton"));
        QIcon icon2;
        icon2.addFile(QStringLiteral(":/icons/icons/jumpIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        jumpButton->setIcon(icon2);
        jumpButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(jumpButton);

        waitButton = new QPushButton(horizontalLayoutWidget);
        waitButton->setObjectName(QStringLiteral("waitButton"));
        QIcon icon3;
        icon3.addFile(QStringLiteral(":/icons/icons/waitIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        waitButton->setIcon(icon3);
        waitButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(waitButton);

        ifButton = new QPushButton(horizontalLayoutWidget);
        ifButton->setObjectName(QStringLiteral("ifButton"));
        QIcon icon4;
        icon4.addFile(QStringLiteral(":/icons/icons/ifIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        ifButton->setIcon(icon4);
        ifButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(ifButton);

        loopButton = new QPushButton(horizontalLayoutWidget);
        loopButton->setObjectName(QStringLiteral("loopButton"));
        QIcon icon5;
        icon5.addFile(QStringLiteral(":/icons/icons/loopIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        loopButton->setIcon(icon5);
        loopButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(loopButton);

        removeButton = new QPushButton(horizontalLayoutWidget);
        removeButton->setObjectName(QStringLiteral("removeButton"));
        QIcon icon6;
        icon6.addFile(QStringLiteral(":/icons/icons/removeLastIcon.ico"), QSize(), QIcon::Normal, QIcon::Off);
        removeButton->setIcon(icon6);
        removeButton->setIconSize(QSize(25, 25));

        commandLayout->addWidget(removeButton);

        commandsLabel = new QLabel(commandFrame);
        commandsLabel->setObjectName(QStringLiteral("commandsLabel"));
        commandsLabel->setGeometry(QRect(0, 0, 71, 16));
        commandsLabel->setFont(font);
        loopLabel = new QLabel(commandFrame);
        loopLabel->setObjectName(QStringLiteral("loopLabel"));
        loopLabel->setGeometry(QRect(720, 0, 81, 20));
        QFont font1;
        font1.setBold(false);
        font1.setWeight(50);
        loopLabel->setFont(font1);
        if1 = new QPushButton(commandFrame);
        if1->setObjectName(QStringLiteral("if1"));
        if1->setGeometry(QRect(530, 20, 41, 31));
        if2 = new QPushButton(commandFrame);
        if2->setObjectName(QStringLiteral("if2"));
        if2->setGeometry(QRect(620, 20, 41, 31));
        loop1 = new QPushButton(commandFrame);
        loop1->setObjectName(QStringLiteral("loop1"));
        loop1->setGeometry(QRect(720, 20, 41, 31));
        loop2 = new QPushButton(commandFrame);
        loop2->setObjectName(QStringLiteral("loop2"));
        loop2->setGeometry(QRect(760, 20, 41, 31));
        loop3 = new QPushButton(commandFrame);
        loop3->setObjectName(QStringLiteral("loop3"));
        loop3->setGeometry(QRect(800, 20, 41, 31));
        loopSpinBox = new QSpinBox(commandFrame);
        loopSpinBox->setObjectName(QStringLiteral("loopSpinBox"));
        loopSpinBox->setGeometry(QRect(809, 3, 31, 16));
        loopSpinBox->setMinimum(1);
        loopSpinBox->setMaximum(4);
        restartButton = new QPushButton(commandFrame);
        restartButton->setObjectName(QStringLiteral("restartButton"));
        restartButton->setGeometry(QRect(860, 10, 81, 41));
        if12 = new QPushButton(commandFrame);
        if12->setObjectName(QStringLiteral("if12"));
        if12->setGeometry(QRect(570, 20, 41, 31));
        if22 = new QPushButton(commandFrame);
        if22->setObjectName(QStringLiteral("if22"));
        if22->setGeometry(QRect(660, 20, 41, 31));
        line = new QFrame(commandFrame);
        line->setObjectName(QStringLiteral("line"));
        line->setGeometry(QRect(615, 0, 3, 61));
        line->setStyleSheet(QStringLiteral("background-color: black"));
        line->setFrameShape(QFrame::VLine);
        line->setFrameShadow(QFrame::Sunken);
        loopLabel_2 = new QLabel(commandFrame);
        loopLabel_2->setObjectName(QStringLiteral("loopLabel_2"));
        loopLabel_2->setGeometry(QRect(622, 0, 81, 20));
        loopLabel_2->setFont(font1);
        loopLabel_3 = new QLabel(commandFrame);
        loopLabel_3->setObjectName(QStringLiteral("loopLabel_3"));
        loopLabel_3->setGeometry(QRect(532, 0, 81, 20));
        loopLabel_3->setFont(font1);
        logoutButton = new QPushButton(centralWidget);
        logoutButton->setObjectName(QStringLiteral("logoutButton"));
        logoutButton->setGeometry(QRect(50, 10, 75, 31));
        levelFrame = new QFrame(centralWidget);
        levelFrame->setObjectName(QStringLiteral("levelFrame"));
        levelFrame->setGeometry(QRect(60, 60, 880, 430));
        levelFrame->setStyleSheet(QStringLiteral("background: rgb(226, 226, 226)"));
        levelFrame->setFrameShape(QFrame::NoFrame);
        levelFrame->setFrameShadow(QFrame::Plain);
        gridLayoutWidget_2 = new QWidget(levelFrame);
        gridLayoutWidget_2->setObjectName(QStringLiteral("gridLayoutWidget_2"));
        gridLayoutWidget_2->setGeometry(QRect(9, 9, 876, 411));
        levelLayout = new QGridLayout(gridLayoutWidget_2);
        levelLayout->setSpacing(6);
        levelLayout->setContentsMargins(11, 11, 11, 11);
        levelLayout->setObjectName(QStringLiteral("levelLayout"));
        levelLayout->setContentsMargins(0, 0, 0, 0);
        level3Layout = new QVBoxLayout();
        level3Layout->setSpacing(6);
        level3Layout->setObjectName(QStringLiteral("level3Layout"));
        level3Label = new QLabel(gridLayoutWidget_2);
        level3Label->setObjectName(QStringLiteral("level3Label"));
        QFont font2;
        font2.setPointSize(11);
        font2.setBold(true);
        font2.setWeight(75);
        level3Label->setFont(font2);
        level3Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level3Layout->addWidget(level3Label);

        level3Button = new QPushButton(gridLayoutWidget_2);
        level3Button->setObjectName(QStringLiteral("level3Button"));
        level3Button->setMinimumSize(QSize(0, 100));
        QIcon icon7;
        icon7.addFile(QStringLiteral(":/icons/icons/level3.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level3Button->setIcon(icon7);
        level3Button->setIconSize(QSize(270, 100));
        level3Button->setFlat(true);

        level3Layout->addWidget(level3Button);


        levelLayout->addLayout(level3Layout, 0, 2, 1, 1);

        level7Layout = new QVBoxLayout();
        level7Layout->setSpacing(6);
        level7Layout->setObjectName(QStringLiteral("level7Layout"));
        level7Label = new QLabel(gridLayoutWidget_2);
        level7Label->setObjectName(QStringLiteral("level7Label"));
        level7Label->setFont(font2);
        level7Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level7Layout->addWidget(level7Label);

        level7Button = new QPushButton(gridLayoutWidget_2);
        level7Button->setObjectName(QStringLiteral("level7Button"));
        level7Button->setMinimumSize(QSize(0, 100));
        QIcon icon8;
        icon8.addFile(QStringLiteral(":/icons/icons/level7.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level7Button->setIcon(icon8);
        level7Button->setIconSize(QSize(270, 100));
        level7Button->setFlat(true);

        level7Layout->addWidget(level7Button);


        levelLayout->addLayout(level7Layout, 2, 0, 1, 1);

        level2Layout = new QVBoxLayout();
        level2Layout->setSpacing(6);
        level2Layout->setObjectName(QStringLiteral("level2Layout"));
        level2Label = new QLabel(gridLayoutWidget_2);
        level2Label->setObjectName(QStringLiteral("level2Label"));
        level2Label->setFont(font2);
        level2Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level2Layout->addWidget(level2Label);

        level2Button = new QPushButton(gridLayoutWidget_2);
        level2Button->setObjectName(QStringLiteral("level2Button"));
        level2Button->setMinimumSize(QSize(0, 100));
        QIcon icon9;
        icon9.addFile(QStringLiteral(":/icons/icons/level2.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level2Button->setIcon(icon9);
        level2Button->setIconSize(QSize(270, 100));
        level2Button->setFlat(true);

        level2Layout->addWidget(level2Button);


        levelLayout->addLayout(level2Layout, 0, 1, 1, 1);

        level4Layout = new QVBoxLayout();
        level4Layout->setSpacing(6);
        level4Layout->setObjectName(QStringLiteral("level4Layout"));
        level4Label = new QLabel(gridLayoutWidget_2);
        level4Label->setObjectName(QStringLiteral("level4Label"));
        level4Label->setFont(font2);
        level4Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level4Layout->addWidget(level4Label);

        level4Button = new QPushButton(gridLayoutWidget_2);
        level4Button->setObjectName(QStringLiteral("level4Button"));
        level4Button->setMinimumSize(QSize(0, 100));
        QIcon icon10;
        icon10.addFile(QStringLiteral(":/icons/icons/level4.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level4Button->setIcon(icon10);
        level4Button->setIconSize(QSize(270, 100));
        level4Button->setFlat(true);

        level4Layout->addWidget(level4Button);


        levelLayout->addLayout(level4Layout, 1, 0, 1, 1);

        level1Layout = new QVBoxLayout();
        level1Layout->setSpacing(6);
        level1Layout->setObjectName(QStringLiteral("level1Layout"));
        level1Label = new QLabel(gridLayoutWidget_2);
        level1Label->setObjectName(QStringLiteral("level1Label"));
        level1Label->setFont(font2);
        level1Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level1Layout->addWidget(level1Label);

        level1Button = new QPushButton(gridLayoutWidget_2);
        level1Button->setObjectName(QStringLiteral("level1Button"));
        level1Button->setMinimumSize(QSize(0, 100));
        QIcon icon11;
        icon11.addFile(QStringLiteral(":/icons/icons/level1.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level1Button->setIcon(icon11);
        level1Button->setIconSize(QSize(270, 100));
        level1Button->setFlat(true);

        level1Layout->addWidget(level1Button);


        levelLayout->addLayout(level1Layout, 0, 0, 1, 1);

        level5Layout = new QVBoxLayout();
        level5Layout->setSpacing(6);
        level5Layout->setObjectName(QStringLiteral("level5Layout"));
        level5Label = new QLabel(gridLayoutWidget_2);
        level5Label->setObjectName(QStringLiteral("level5Label"));
        level5Label->setFont(font2);
        level5Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level5Layout->addWidget(level5Label);

        level5Button = new QPushButton(gridLayoutWidget_2);
        level5Button->setObjectName(QStringLiteral("level5Button"));
        level5Button->setMinimumSize(QSize(0, 100));
        QIcon icon12;
        icon12.addFile(QStringLiteral(":/icons/icons/level5.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level5Button->setIcon(icon12);
        level5Button->setIconSize(QSize(270, 100));
        level5Button->setFlat(true);

        level5Layout->addWidget(level5Button);


        levelLayout->addLayout(level5Layout, 1, 1, 1, 1);

        level6Layout = new QVBoxLayout();
        level6Layout->setSpacing(6);
        level6Layout->setObjectName(QStringLiteral("level6Layout"));
        level6Label = new QLabel(gridLayoutWidget_2);
        level6Label->setObjectName(QStringLiteral("level6Label"));
        level6Label->setFont(font2);
        level6Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level6Layout->addWidget(level6Label);

        level6Button = new QPushButton(gridLayoutWidget_2);
        level6Button->setObjectName(QStringLiteral("level6Button"));
        level6Button->setMinimumSize(QSize(0, 100));
        QIcon icon13;
        icon13.addFile(QStringLiteral(":/icons/icons/level6.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level6Button->setIcon(icon13);
        level6Button->setIconSize(QSize(270, 100));
        level6Button->setFlat(true);

        level6Layout->addWidget(level6Button);


        levelLayout->addLayout(level6Layout, 1, 2, 1, 1);

        level9Layout = new QVBoxLayout();
        level9Layout->setSpacing(6);
        level9Layout->setObjectName(QStringLiteral("level9Layout"));
        level9Label = new QLabel(gridLayoutWidget_2);
        level9Label->setObjectName(QStringLiteral("level9Label"));
        level9Label->setFont(font2);
        level9Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level9Layout->addWidget(level9Label);

        level9Button = new QPushButton(gridLayoutWidget_2);
        level9Button->setObjectName(QStringLiteral("level9Button"));
        level9Button->setMinimumSize(QSize(0, 100));
        QIcon icon14;
        icon14.addFile(QStringLiteral(":/icons/icons/level9.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level9Button->setIcon(icon14);
        level9Button->setIconSize(QSize(270, 100));
        level9Button->setFlat(true);

        level9Layout->addWidget(level9Button);


        levelLayout->addLayout(level9Layout, 2, 2, 1, 1);

        level8Layout = new QVBoxLayout();
        level8Layout->setSpacing(6);
        level8Layout->setObjectName(QStringLiteral("level8Layout"));
        level8Label = new QLabel(gridLayoutWidget_2);
        level8Label->setObjectName(QStringLiteral("level8Label"));
        level8Label->setFont(font2);
        level8Label->setStyleSheet(QStringLiteral("qproperty-alignment: AlignCenter;"));

        level8Layout->addWidget(level8Label);

        level8Button = new QPushButton(gridLayoutWidget_2);
        level8Button->setObjectName(QStringLiteral("level8Button"));
        level8Button->setMinimumSize(QSize(0, 100));
        QIcon icon15;
        icon15.addFile(QStringLiteral(":/icons/icons/level8.PNG"), QSize(), QIcon::Normal, QIcon::Off);
        level8Button->setIcon(icon15);
        level8Button->setIconSize(QSize(270, 100));
        level8Button->setFlat(true);

        level8Layout->addWidget(level8Button);


        levelLayout->addLayout(level8Layout, 2, 1, 1, 1);

        userStatsLabel = new QLabel(centralWidget);
        userStatsLabel->setObjectName(QStringLiteral("userStatsLabel"));
        userStatsLabel->setGeometry(QRect(220, 12, 731, 21));
        QFont font3;
        font3.setPointSize(14);
        font3.setBold(true);
        font3.setWeight(75);
        userStatsLabel->setFont(font3);
        sequenceFrame = new QFrame(centralWidget);
        sequenceFrame->setObjectName(QStringLiteral("sequenceFrame"));
        sequenceFrame->setGeometry(QRect(989, 49, 101, 521));
        sequenceFrame->setFrameShape(QFrame::StyledPanel);
        sequenceFrame->setFrameShadow(QFrame::Raised);
        verticalLayoutWidget = new QWidget(sequenceFrame);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(0, 20, 101, 431));
        sequenceLayout = new QVBoxLayout(verticalLayoutWidget);
        sequenceLayout->setSpacing(6);
        sequenceLayout->setContentsMargins(11, 11, 11, 11);
        sequenceLayout->setObjectName(QStringLiteral("sequenceLayout"));
        sequenceLayout->setContentsMargins(0, 0, 0, 0);
        runButton = new QPushButton(sequenceFrame);
        runButton->setObjectName(QStringLiteral("runButton"));
        runButton->setGeometry(QRect(10, 470, 75, 41));
        sequenceLabel = new QLabel(sequenceFrame);
        sequenceLabel->setObjectName(QStringLiteral("sequenceLabel"));
        sequenceLabel->setGeometry(QRect(0, 0, 61, 16));
        sequenceLabel->setFont(font);
        scoreFrame = new QFrame(centralWidget);
        scoreFrame->setObjectName(QStringLiteral("scoreFrame"));
        scoreFrame->setGeometry(QRect(50, 50, 901, 451));
        scoreFrame->setStyleSheet(QStringLiteral("background: rgb(226, 226, 226)"));
        scoreFrame->setFrameShape(QFrame::StyledPanel);
        scoreFrame->setFrameShadow(QFrame::Raised);
        scoreGrid = new QFrame(scoreFrame);
        scoreGrid->setObjectName(QStringLiteral("scoreGrid"));
        scoreGrid->setGeometry(QRect(280, 120, 311, 231));
        scoreGrid->setFrameShape(QFrame::StyledPanel);
        scoreGrid->setFrameShadow(QFrame::Raised);
        gridLayoutWidget_3 = new QWidget(scoreGrid);
        gridLayoutWidget_3->setObjectName(QStringLiteral("gridLayoutWidget_3"));
        gridLayoutWidget_3->setGeometry(QRect(20, 10, 271, 191));
        scoreLayout = new QGridLayout(gridLayoutWidget_3);
        scoreLayout->setSpacing(6);
        scoreLayout->setContentsMargins(11, 11, 11, 11);
        scoreLayout->setObjectName(QStringLiteral("scoreLayout"));
        scoreLayout->setContentsMargins(0, 0, 0, 0);
        scoreValue = new QLabel(gridLayoutWidget_3);
        scoreValue->setObjectName(QStringLiteral("scoreValue"));
        scoreValue->setFont(font3);
        scoreValue->setFrameShape(QFrame::NoFrame);

        scoreLayout->addWidget(scoreValue, 1, 1, 1, 1);

        highschoolValue = new QLabel(gridLayoutWidget_3);
        highschoolValue->setObjectName(QStringLiteral("highschoolValue"));
        highschoolValue->setFont(font3);
        highschoolValue->setFrameShape(QFrame::NoFrame);
        highschoolValue->setLineWidth(1);

        scoreLayout->addWidget(highschoolValue, 2, 1, 1, 1);

        continueButton = new QPushButton(gridLayoutWidget_3);
        continueButton->setObjectName(QStringLiteral("continueButton"));

        scoreLayout->addWidget(continueButton, 3, 1, 1, 1);

        highscoreLabel = new QLabel(gridLayoutWidget_3);
        highscoreLabel->setObjectName(QStringLiteral("highscoreLabel"));
        highscoreLabel->setFont(font3);
        highscoreLabel->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);

        scoreLayout->addWidget(highscoreLabel, 2, 0, 1, 1);

        scoreLabel = new QLabel(gridLayoutWidget_3);
        scoreLabel->setObjectName(QStringLiteral("scoreLabel"));
        scoreLabel->setFont(font3);
        scoreLabel->setAlignment(Qt::AlignRight|Qt::AlignTrailing|Qt::AlignVCenter);

        scoreLayout->addWidget(scoreLabel, 1, 0, 1, 1);

        scoreGrid->raise();
        loginFrame->raise();
        loginFrame->raise();
        loginFrame->raise();
        homeButton = new QPushButton(centralWidget);
        homeButton->setObjectName(QStringLiteral("homeButton"));
        homeButton->setGeometry(QRect(130, 10, 75, 31));
        studentStats = new QPushButton(centralWidget);
        studentStats->setObjectName(QStringLiteral("studentStats"));
        studentStats->setGeometry(QRect(990, 10, 101, 31));
        MainWindow->setCentralWidget(centralWidget);
        commandFrame->raise();
        logoutButton->raise();
        userStatsLabel->raise();
        sequenceFrame->raise();
        homeButton->raise();
        levelFrame->raise();
        studentStats->raise();
        scoreFrame->raise();
        loginFrame->raise();
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 1117, 21));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0));
        loginFailedLabel->setText(QApplication::translate("MainWindow", "Login failed! Please try again.", 0));
        createLoginButton->setText(QApplication::translate("MainWindow", "Sign up!", 0));
        createLoginLabel->setText(QApplication::translate("MainWindow", "Are you a new user?", 0));
        instructorLabel->setText(QApplication::translate("MainWindow", "Instructor?", 0));
        instructorNameLabel->setText(QApplication::translate("MainWindow", "Instructor:", 0));
        lastNameLabel->setText(QApplication::translate("MainWindow", "Last name:", 0));
        yesBox->setText(QApplication::translate("MainWindow", "Yes", 0));
        noBox->setText(QApplication::translate("MainWindow", "No", 0));
        firstNameLabel->setText(QApplication::translate("MainWindow", "First name:", 0));
        signUpFinalButton->setText(QApplication::translate("MainWindow", "Sign Up", 0));
        cancelButton->setText(QApplication::translate("MainWindow", "Cancel", 0));
        loginOffline->setText(QApplication::translate("MainWindow", "Offline Login", 0));
        loginOfflineLabel->setText(QApplication::translate("MainWindow", "No Internet?", 0));
        passwordLabel->setText(QApplication::translate("MainWindow", "Password:", 0));
        usernameLabel->setText(QApplication::translate("MainWindow", "Username:", 0));
        loginButton->setText(QApplication::translate("MainWindow", "Login", 0));
        splashScreen->setText(QString());
        forwardButton->setText(QApplication::translate("MainWindow", "Forward();", 0));
        jumpButton->setText(QApplication::translate("MainWindow", "Jump();", 0));
        waitButton->setText(QApplication::translate("MainWindow", "Wait();", 0));
        ifButton->setText(QApplication::translate("MainWindow", "If()", 0));
        loopButton->setText(QApplication::translate("MainWindow", "Loop()", 0));
        removeButton->setText(QApplication::translate("MainWindow", "Remove Last", 0));
        commandsLabel->setText(QApplication::translate("MainWindow", "Commands:", 0));
        loopLabel->setText(QApplication::translate("MainWindow", "Loop Commands:", 0));
        if1->setText(QString());
        if2->setText(QString());
        loop1->setText(QString());
        loop2->setText(QString());
        loop3->setText(QString());
        restartButton->setText(QApplication::translate("MainWindow", "Restart Level", 0));
        if12->setText(QString());
        if22->setText(QString());
        loopLabel_2->setText(QApplication::translate("MainWindow", "Else Commands:", 0));
        loopLabel_3->setText(QApplication::translate("MainWindow", "If Commands:", 0));
        logoutButton->setText(QApplication::translate("MainWindow", "Logout?", 0));
        level3Label->setText(QApplication::translate("MainWindow", "Level 3:", 0));
        level3Button->setText(QString());
        level7Label->setText(QApplication::translate("MainWindow", "Level 7:", 0));
        level7Button->setText(QString());
        level2Label->setText(QApplication::translate("MainWindow", "Level 2:", 0));
        level2Button->setText(QString());
        level4Label->setText(QApplication::translate("MainWindow", "Level 4:", 0));
        level4Button->setText(QString());
        level1Label->setText(QApplication::translate("MainWindow", "Level 1:", 0));
        level1Button->setText(QString());
        level5Label->setText(QApplication::translate("MainWindow", "Level 5:", 0));
        level5Button->setText(QString());
        level6Label->setText(QApplication::translate("MainWindow", "Level 6:", 0));
        level6Button->setText(QString());
        level9Label->setText(QApplication::translate("MainWindow", "Level 9:", 0));
        level9Button->setText(QString());
        level8Label->setText(QApplication::translate("MainWindow", "Level 8:", 0));
        level8Button->setText(QString());
        userStatsLabel->setText(QApplication::translate("MainWindow", "Welcome", 0));
        runButton->setText(QApplication::translate("MainWindow", "Run it!!", 0));
        sequenceLabel->setText(QApplication::translate("MainWindow", "Sequence:", 0));
        scoreValue->setText(QString());
        highschoolValue->setText(QString());
        continueButton->setText(QApplication::translate("MainWindow", "Continue", 0));
        highscoreLabel->setText(QApplication::translate("MainWindow", "Highscore:", 0));
        scoreLabel->setText(QApplication::translate("MainWindow", "Score:", 0));
        homeButton->setText(QApplication::translate("MainWindow", "Home", 0));
        studentStats->setText(QApplication::translate("MainWindow", "View Student Stats", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
