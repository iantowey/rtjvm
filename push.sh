cur_dir=`pwd`
echo $cur_dir
project_name=$(basename $cur_dir)
echo $project_name

sbt clean

cd $tmp
git clone https://github.com/iantowey/rtjvm.git

cp -r $cur_dir/* /tmp/$project_name/
