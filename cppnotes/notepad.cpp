#include "notepad.h"
// #include <QTextEdit>
#include <QTextEdit>
#include <QMainWindow>
#include <QTextEdit>
#include <QPushButton>
#include <QAction>
#include <QMenu>
#include <QObject>
#include <QFileDialog>
#include <QFile>
#include <QMessageBox>
#include <QIODevice>
#include <QIODevice>
#include <QApplication>


notepad::notepad() {
    tEditor = new QTextEdit;
    quitButton = new QPushButton;

    openAction = new QAction(tr("Open"), this);
    saveAction = new QAction(tr("Save"), this);
    quitAction = new QAction(tr("Quit"), this);

    QObject::connect(openAction, SIGNAL(triggered()), this, SLOT(open()));
    QObject::connect(saveAction, SIGNAL(triggered()), this, SLOT(save()));
    QObject::connect(quitAction, SIGNAL(triggered()), this, SLOT(quit()));

    fileMenu = menuBar()->addMenu(tr("File"));
    fileMenu->addAction(openAction);
    fileMenu->addAction(saveAction);
    fileMenu->addAction(quitAction);

    setWindowTitle(tr("Notepad"));
    setCentralWidget(tEditor);
}

void notepad::open() {
    QString fileName = QFileDialog::getOpenFileName(this, tr("Select a file..."), "", tr("Text Files (*.txt);;c++ source files (*.cpp);;All files (*.*)"));
    if (fileName!="") {
        QFile fIn(fileName);
        if(!fIn.open(QIODevice::ReadOnly)) {
            QMessageBox::critical(this, tr("Error"), tr("File could not be opened"));
            return;
        }
        QTextStream in(&fIn);
        tEditor->setText(in.readAll());
        fIn.close();
    } else
    {
        QMessageBox confirm;
        confirm.setText(tr("No file has been selected"));
        confirm.setStandardButtons(QMessageBox::Retry | QMessageBox::Ok);
        confirm.setDefaultButton(QMessageBox::Ok);
    
        if(confirm.exec()==QMessageBox::Retry) {
            open();
        }
    }
}

void notepad::save() {
    QString fileName = QFileDialog::getSaveFileName(this, tr("Save File"), "", tr("Text Files (*.txt);;c++ source files (*.cpp);;All files (*.*)"));
    QFile fOut(fileName);
    if(fileName!="") {
        if(fOut.open(QIODevice::WriteOnly)) {
            QTextStream out(&fOut);
            out<<tEditor->toPlainText();
            out.flush();
            fOut.close();
        }
        else {
            QMessageBox::critical(this, tr("Error !!!"), tr("File could not be saved"));
            return;
        }
    }
}

void notepad::quit() {
    QMessageBox confirmQuit;
    confirmQuit.setStandardButtons(QMessageBox::Yes | QMessageBox::No);
    confirmQuit.setDefaultButton(QMessageBox::No);
    confirmQuit.setText(tr("Do you really want to quit?"));
    confirmQuit.setWindowTitle(tr("Close the notepad"));
    if (confirmQuit.exec() == QMessageBox::Yes) {
        qApp->exec();
    }
}