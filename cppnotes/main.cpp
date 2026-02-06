#include "notepad.h"
#include <QApplication>

int main(int argc, char **argv)
{
    QApplication nPad(argc, argv);
    notepad *window = new notepad();
    window->show();
    return nPad.exec();
}