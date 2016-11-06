#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QFileDialog>
#include <time.h>

using namespace std;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    ui->pushButton->setEnabled(false);

    connect(ui->openFileAction, SIGNAL(triggered(bool)), this, SLOT(openFileClicked()));
    connect(ui->pushButton, SIGNAL(clicked(bool)), this, SLOT(genClicked()));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::openFileClicked()
{
    fileName = QFileDialog::getOpenFileName(this, tr("Open File"),
                                                "./",
                                                tr("TXT (*.txt)"));
    if (!fileName.isEmpty())
    {
        populateCells();
    }
}

void MainWindow::populateCells()
{
    ui->pushButton->setEnabled(true);

    QFile file(fileName);
    if (!file.open(QIODevice::ReadOnly | QIODevice::Text))
        return;

    QString contentOld = file.readAll();

    file.close();

    contentOld = contentOld.toLower();

    QString content = clean(contentOld);

    QStringList list = content.split(" ");
    list2 = content.split(" ");

    list2.sort();

    list2.removeDuplicates();
    //list2.removeAt(0);

    int i = 0;

    ui->wordTable->setRowCount(list2.size());
    ui->wordTable->setColumnCount(list2.size());

    ui->wordTable->setHorizontalHeaderLabels(list2);
    ui->wordTable->setVerticalHeaderLabels(list2);

    for (int i = 0; i<list2.size(); i++)
    {
        for (int j = 0; j<list2.size(); j++)
        {
            ui->wordTable->setItem(i, j, new QTableWidgetItem);
        }
    }

    for (int i = 0; i<list2.size(); i++)
    {
        for (int j = 0; j<list2.size(); j++)
        {
            ui->wordTable->item(i,j)->setText("0");
        }
    }

    for (int i = 0; i < list.size()-1; i++)
    {
        QString word1 = list.at(i);
        QString word2 = list.at(i+1);
        int index1;
        int index2;

        index1 = list2.indexOf(word1);
        index2 = list2.indexOf(word2);

        int base = ui->wordTable->item(index1, index2)->text().toInt();
        base++;
        QString newText = QString::number(base);
        ui->wordTable->item(index2, index1)->setText(newText);
    }
}

void MainWindow::genClicked()
{
    srand (time(NULL));
    ui->gen1->setText(genRandom());
    ui->gen2->setText(genRandom());
    ui->gen3->setText(trulyRandom());
    ui->gen4->setText(trulyRandom());
}

QString MainWindow::genRandom()
{
    int highest = 0;
    QString ran1 = "";
    int ran = rand() % list2.size() +1;
    ran1.append(" ");
    ran1.append(list2.at(ran));

    vector<QString> combo;

    for (int i = 0; i < 9; i++)
    {
        ran1.append(" ");
        for (int i = 0; i < list2.size(); i++)
        {
            if (ui->wordTable->item(i,ran)->text().toInt() > 0)
                highest = ui->wordTable->item(i,ran)->text().toInt();
        }
        for (int i = 0; i < list2.size(); i++)
        {
            if (ui->wordTable->item(i,ran)->text().toInt() == highest)
                combo.push_back(list2.at(i));
        }
        int ranNew = rand() % combo.size();
        ran1.append(combo.at(ranNew));

        ran = list2.indexOf(combo.at(ranNew));
        combo.clear();
    }
    return ran1;
}

QString MainWindow::trulyRandom()
{
    QString ran1 = "";
    for (int i = 0; i < 10; i++)
    {
        ran1.append(" ");
        int ran = rand() % list2.size() +1;
        ran1.append(list2.at(ran));
    }
    ran1.append(".");
    return ran1;
}

QString MainWindow::clean(QString str)
{
    str.remove(QRegExp(QString::fromUtf8("[-`~!@#$%^&*()_—+=|:;<>«»,.?/{}\'\n\"\\\[\\\]\\\\]")));
    return str;
}
