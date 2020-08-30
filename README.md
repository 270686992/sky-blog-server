<h1 align="center">
  Sky-Blog C 端服务端
</h1>

<p align="center">

  <a href="https://spring.io/" rel="nofollow">
  <img src="https://img.shields.io/badge/spring%20boot-2.2.6.RELEASE-green" alt="spring boot" data-canonical-src="https://img.shields.io/badge/spring%20boot-2.2.6.RELEASE-green" style="max-width:100%;">
  </a>
  
  <a href="https://spring.io/" rel="nofollow">
  <img src="https://img.shields.io/badge/jpa-2.2.6-green" alt="jpa" data-canonical-src="https://img.shields.io/badge/jpa-2.2.6-green" style="max-width:100%;">
  </a>
  
  <a href="https://choosealicense.com/licenses/mit/" rel="nofollow">
  <img src="https://img.shields.io/badge/license-MIT-lightgrey.svg" alt="LISENCE" data-canonical-src="https://img.shields.io/badge/license-MIT-lightgrey.svg" style="max-width:100%;">
  </a>
  
</p>

<blockquote align="center">
 本仓库为 Sky-Blog 的 C 端的服务端仓库,负责博客前端的 API 接口的管理。
</blockquote>

<p align="center">
  <a href="##预览">预览</a>&nbsp;|&nbsp;
  <a href="##简介">简介</a>&nbsp;|&nbsp;
  <a href="##如何使用">如何使用</a>
</p>

## 预览

### 线上 demo

- 博客地址: [www.xilikeli.cn](https://www.xilikeli.cn)

- CMS 端地址: [admin.xilikeli.cn](https://admin.xilikeli.cn)
    - 游客用户的账号密码: guest, 123456

## 简介

博客前端查看请访问 [博客前端仓库](https://github.com/270686992/sky-blog-frontend)

CMS 后端请访问 [后端仓库](https://github.com/270686992/sky-blog-cms-server)

CMS 前端请访问 [前端仓库](https://github.com/270686992/sky-blog-cms-frontend)

CMS 的后端和前端基于 Lin CMS 的基础进行开发, Lin CMS 地址: [https://github.com/TaleLin](https://github.com/TaleLin)

## 如何使用

1. 将项目依赖引入之后, 首先使用下列语句创建数据库(数据库只需引入一次, CMS 端和 C 端使用同一个数据库)

    ```sql
    drop database if exists sky_blog;
    create database sky_blog default character set utf8mb4 collate utf8mb4_general_ci;
    use sky_blog;
    ```

2. 接着在此数据库中导入项目 resources 目录下的 schema.sql 和 sky_blog.sql 文件。

3. 接着将 application.yml 中的邮箱配置和项目业务相关配置中的配置填写为自己的配置。

4. 接着将 application-dev.yml 和 application-prod.yml 中的数据源配置和 reids 相关的配置切换为自己的配置。

5. 接着修改项目 resources 目录下的 static/template 目录下的邮件模板文件的信息为自己的信息。

6. 完成以上步骤即可启动该仓库的项目,默认本地运行的端口号为 8081, 启动前需开启 redis 服务。

