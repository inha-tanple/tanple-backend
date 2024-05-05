
#!/bin/sh
dir=$(dirname "$0")


touch ~/tanple.mv.db

java -cp "$dir/h2-2.2.224.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Console "$@"
