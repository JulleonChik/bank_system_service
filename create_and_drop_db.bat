@echo off

REM Переменные для подключения к базе данных
SET USER=postgres
SET HOST=localhost
SET PORT=5432
SET DB_NAME=bank_operation_service

:CREATE
echo Создание базы данных...
createdb -U %USER% -h %HOST% -p %PORT% -E utf8 -O %USER% %DB_NAME%
echo База данных успешно создана.
goto END

:DROP
echo Удаление базы данных...
dropdb -U %USER% -h %HOST% -p %PORT% %DB_NAME%
echo База данных успешно удалена.
goto END

:USAGE
echo Используйте 'create_and_drop_db.bat create' для создания базы данных и 'create_and_drop_db.bat drop' для удаления.
goto END
:END
