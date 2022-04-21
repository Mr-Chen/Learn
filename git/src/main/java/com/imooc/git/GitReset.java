package com.imooc.git;

/**
 * Author   ： cxw
 * Date     ： 2022/4/21 08:40
 * Explain  :  回滚
 *
 * git status 查看状态
 * git log 查看提交日志
 * git log -5 --pretty=oneline  log输出格式化
 *
 *  git add --all  添加所有工作区文件到暂存区
 *  git commit -m "说明" 将暂存区的文件提交到本地仓库
 * git reset --hard HEAD^ ^代表上一个版本可以添加多个
 * git reset --hard HEAD~3回滚到上三个版本
 * git reset --hard 232ce 232ce代表log id
 *
 */
public class GitReset {
    String one = "第一个版本";
}
