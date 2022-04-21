package com.imooc.git;

/**
 * Author   ： cxw
 * Date     ： 2022/4/21 11:31
 * Explain  :  标签
 *
 * git tag 查看所有标签
 * git tag v1.0 新建标签 默认为HEAD
 * git tag -a v1.0 -m "标签描述信息" 新建标签并添加描述信息
 * git tag -d v1.0 删除本地标签
 * git push origin v1.0 推送本地标签到远程
 * git push origin --tags 推送全部未推送的本地标签到远程
 * git push origin :refs/tags/v1.0 删除远程标签
 */
public class GitTag {
}
