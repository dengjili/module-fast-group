### 远程分支

介绍
 - release分支为master
 - 开发分支为dev
 - bug分支为bug  (暂不使用)
 
同步代码方向
master <-  dev
master <-  bug  (暂不使用)

### 本地分支
 本地同步远程dev与bug分支，平时根据dev或bug分支创建自己的分支，如dengjili
 
 - 实际工作空间为dengjili分支
 - 提交代码思路，本地dev分支先进行git pull请求，然后dengjili -> dev，然后 本地dev -> 远程dev