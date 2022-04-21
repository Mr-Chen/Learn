package com.imooc.git;

/**
 * Author   ： cxw
 * Date     ： 2022/4/21 09:52
 * Explain  :  分支
 * <p>
 * gitc fetch 读取远程仓库最新状态
 * git branch 查看所有分支 *标记当前所在分支
 * git branch -a 查看本地与远程分支(all)
 * git branch dev 新建分支
 * git checkout -b dev 新建分支并切换到该分支
 * <p>
 * git branch -d dev 删除分支
 * git checkout dev 切换分支
 * git merge dev 合并分支（切换到主分支，在主分支合并）
 * <p>
 * git branch -m dev 重命名
 * git branch -m | M oldName newName 如果名字已经存在强制重命名
 * <p>
 * git push origin master 推送本地分支到远程
 * git push origin :branch_name 删除远程分支，本地保留
 * git checkout -b local_branch origin/remote_branch 拉取远程指定分支并在本地创建分支
 * <p>
 * git branch -u remote_branch  新建分支关联远程分支
 * <p>
 * <p>
 * git branch -vv 查看分支与远程分支绑定关系
 */
public class GitBranch {
    String dev = "分支";
    String conflict = "new_master分支";
}
