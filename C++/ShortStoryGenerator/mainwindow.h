#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private:
    Ui::MainWindow *ui;
    QString fileName;

    void populateCells();
    QString trulyRandom();
    QString genRandom();
    QString clean(QString);
    QStringList list2;

private slots:
    void openFileClicked();
    void genClicked();
};

#endif // MAINWINDOW_H
