package servlet;

import dao.CardDao;
import dao.EmployeeDao;
import dao.UserDao;
import pojo.Card;
import pojo.Employee;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

/***
 * create by yjm
 * 实现用户登录功能
 * 调用UserDao的findUser()方法，若返回结果为true则证明数据库中并无此用户显示登录失败
 * 若返回结果为false则证明子用户已注册，跳转至index页面并使用Cookie保存用户数据。
 * 保存用户名的cookie名字为“ECardUserName”
 * 保存密码的cookie名字为“ECardPassword”
 */

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //测试使用
        //随机插入数据
//        try {
//            randomInsertData();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        //测试使用
        UserDao userDao = new UserDao();
        String id = req.getParameter("LoginUserId");
        String password = req.getParameter("LoginPassword");
        User user = null;
        HttpSession session = req.getSession();
        try {
            //查找是否存在此用户
            user=userDao.getUserByIP(id,password);
            if (user!=null) {
                //将用户账号使用cookie保存
//                Cookie [] cookies=req.getCookies();
//                for (Cookie cookie:cookies){
//                    if ("ECardUserId".equals(cookie.getName())){
//
//                    }
//                }
                Cookie nameCookie = new Cookie("ECardUserId", id);
                Cookie pwCookie = new Cookie("ECardPassword", password);
                nameCookie.setMaxAge(60 * 60 * 24 * 7);//cookie保存一周时间
                pwCookie.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(nameCookie);
                resp.addCookie(pwCookie);
                //更新登录状态
                session.setAttribute("loginState", true);
                session.setAttribute("user", user);
                String role = null;
                //判断用户身份
                if (userDao.isManager(user.getId())) {
                    //用户为管理员
                    role = "Manager";
                } else if (userDao.isHR(user.getId())) {
                    //用户为HR
                    // 保存公司号
                    session.setAttribute("employeeCompany", userDao.findCompany(user.getId()));
                    role = "HR";
                } else {
                    //用户为普通员工
                    role = "CommonEmployee";
                }
                //用session保存用户角色
                session.setAttribute("role", role);
                //保存用户信息（密码及id）
                session.setAttribute("user", user);
                //加载个人名片界面并跳转
                resp.sendRedirect("inner/LoadCard");
            } else {//查询不到用户，可能是用户名/密码错误
                // 更新登录状态
                session.setAttribute("loginState", false);
                // 重定向至登录界面
                resp.sendRedirect("login.jsp");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    private String getGirlName() {
        String girlName = "菡 娆 炫 源 卉 娘 蕊 娜 纤 蔓 凡 怡 蒙 嫔 敏 花 叶 琰 汇 妃 莲 娥 娴 雪 露 素 菁 然 青 艳 薰 苑 莺 晶 岚 卿 香 艺 亚 滟 璟 娉 爽 霄 美 瑗 惠 婧 霭 风 水 影 月 蓓 靖 平 纨 嵘 呤 柳 蓝 眉 评 聪 丹 咏 秋 银 茗 丝 宛 晓 悠 曼 明 静 苹 菀 晴 诗 玥 紫 宁 舒 囡 心 俞 楚 漫 璧 梅 娈 芯 可 炎 玟 林 屏 忆 音 姹 妙 慧 蓉 嫦 若 代 华 瑶 念 英 莓 婉 蝶 淼 悦 火 姗 莉 盈 欣 如 馥 姬 馡 依 谷 翎 瑜 娅 珆 燕 菱 琳 伶 羽 越 姞 菊 萍 琬 荣 梓 枫 冽 娟 芮 薇 炅 思 云 佳 君 艾 烟 瑞 含 芸 玲 茵 叆 好 歆 嫣 韵 嘉 筠 琪 媚 媱 珴 昭 冰 珂 纯 宜 琼 妮 芳 妆 姳 琦 冉 蕾 海 涵 白 咛 姲 荭 馨 淞 怀 珊 伊 棋 文 星 雨 贞 丽 凝 绮 仪 楠 语 珍 姜 奴 芊 梦 秀 玉 草 菲 女 茜 纹 旭 渺 姝 娇 桃 霜 雯 絮 育 涟 姣 偀 彤 妩 萱 采 偲 妹 凤 倩 南 亿 钰 璐 洁 盼 嫱 姯 滢 彨 颜 玫 婵 柔 希 妶 倪 茹 芬 清 红 翠 旋 煜 真 碧 赫 慕 曦 雁 瑷 芝 姑 婷 漪 宝 桂 竹 欢 姿 澜 彩 冷 听 画 枝 婕 淑 芙 禧 波 雅 芷 姐 沛 巧 霖 萌 晗 荔 莎 兰 怜 寻 黛 毓 珠 春 俪 晨 莹 容 妍 寒 锦 佩 芹 娣 灵 园 烁 倰 瑛 琴 情 漩 媛 环 霏 芃 湾 贻 璇 荷 嫂 檀 融 勤 霞 颖 安 幻 瑾 飘 爱";
        String[] girlNames = girlName.split(" ");
        Random random = new Random();
        int index = random.nextInt(girlNames.length);
        return girlNames[index];
    }

    private String getBoyName() {
        String boyName = "彬 轩 含 蒲 乒 虚 行 亭 仑 蓝 影 韬 函 克 盛 衡 芝 晗 昊 诗 琦 至 涵 伦 时 映 志 菱 纶 士 永 致 嘉 旷 示 咏 智 安 轮 世 勇 中 昂 律 业 友 忠 敖 齐 轼 桓 林 言 群 书 有 宣 颁 略 伟 骢 州 清 宏 充 佑 洲 庭 马 濮 丹 乐 邦 迈 卫 平 乾 榜 宸 蔚 旲 东 宝 昴 树 材 纪 保 茂 泓 棋 竹 葆 浩 魏 妤 铸 劻 玫 晔 渝 壮 羚 阳 文 瑜 卓 掣 奎 船 与 萱 豹 梅 汶 旭 濯 驾 和 航 宇 孜 邶 望 武 羽 崊 霆 美 希 雨 淑 冰 蒙 才 凰 腾 备 密 溪 泰 子 辈 冕 帅 语 茜 蓓 淼 曦 玉 梓 弼 民 奇 禾 综 碧 洋 霞 连 祖 厚 晨 先 昱 选 昪 旻 虹 朔 济 彪 淏 贤 儋 冬 龄 馗 娴 钰 栋 飙 传 舷 御 端 澜 然 磊 裕 段 挺 名 春 誉 天 飚 明 灏 堂 碫 莱 鸣 双 渊 琳 坚 茗 一 元 倩 宾 村 宪 辉 铎 妍 铭 献 彭 思 策 谋 祥 序 伯 骞 牧 翔 启 恩 建 慕 向 沅 发 汗 穆 骁 溓 帆 健 恒 洪 媛 汉 键 威 晓 源 冀 勒 成 笑 远 弘 龙 仁 蕾 棠 凡 江 魁 伊 德 方 城 铿 顺 月 飞 萍 皓 朴 悦 学 骄 楠 啸 绪 强 鲛 妮 勰 跃 霖 劼 宁 兵 越 芬 杰 弩 淳 起 丰 洁 攀 心 云 风 柴 旁 昕 会 沣 婕 薇 欣 良 泊 同 沛 新 芸 川 悍 佩 依 颇 封 金 松 鸿 耘 峰 岩 日 竦 韵 勋 辰 朋 沂 坤 骥 晴 岚 怡 泽 锋 津 荣 信 增 澔 锦 容 立 波 乔 瑾 鹏 宜 登 凤 进 铖 达 承 豪 晋 榕 华 展 福 菁 韦 以 章 俯 彤 融 来 彰 恬 景 力 亿 涛 辅 炎 茹 义 梁 迅 璟 儒 瀚 浦 富 禅 采 艺 基 澉 颔 襦 星 钊 刚 庆 锐 议 昭 博 珑 斌 亦 照 纲 敬 瑞 佚 哲 合 靖 澎 励 喆 佳 驹 睿 易 绮 钢 聚 垒 奕 真 苓 万 尧 益 臻 阔 颜 若 淇 焘 聪 涓 飒 骅 沧 罡 娟 弛 朗 帝 高 军 森 兴 缜 歌 钧 砂 大 畅 弓 筠 山 谊 亮 功 丞 河 逸 稹 巩 全 善 意 舱 固 俊 超 溢 振 钦 隆 频 毅 朕 冠 翰 候 利 谦 部 彦 为 茵 震 谱 韩 劭 英 理 廷 昌 绍 琪 滔 家 骏 社 雄 镇 凌 珺 升 崇 征 光 竣 生 鹰 正 广 凯 圣 迎 诤 晷 铠 驰 寒 政 贵 康 胜 桦 琛 国 泉 晟 盈 殿 海 科 礼 代 之 卿 诚 耀 滢 吉 鑫 谚 亨 瀛 舜 延 可 维 逸";
        String[] boyNames = boyName.split(" ");
        Random random = new Random();
        int index = random.nextInt(boyNames.length);
        return boyNames[index];
    }

    private String getFirstName() {
        String nameString = "赵 钱 孙 李 周 吴 郑 王 冯 陈 楮 卫 蒋 沈 韩 杨 朱 秦 尤 许 何 吕 施 张 孔 曹 严 华 金 魏 陶 姜 戚 谢 邹 喻 柏 水 窦 章 云 苏 潘 葛 奚 范 彭 郎 鲁 韦 昌 马 苗 凤 花 方 俞 任 袁 柳 酆 鲍 史 唐 费 廉 岑 薛 雷 贺 倪 汤 滕 殷 罗 毕 郝 邬 安 常 乐 于 时 傅 皮 卞 齐 康 伍 余 元 卜 顾 孟 平 黄 和 穆 萧 尹 姚 邵 湛 汪 祁 毛 禹 狄 米 贝 明 臧 计 伏 成 戴 谈 宋 茅 庞 熊 纪 舒 屈 项 祝 董 梁 杜 阮 蓝 闽 席 季 麻 强 贾 路 娄 危 江 童 颜 郭 梅 盛 林 刁 锺 徐 丘 骆 高 夏 蔡 田 樊 胡 凌 霍 虞 万 支 柯 昝 管 卢 莫 经 房 裘 缪 干 解 应 宗 丁 宣 贲 邓 郁 单 杭 洪 包 诸 左 石 崔 吉 钮 龚 程 嵇 邢 滑 裴 陆 荣 翁 荀 羊 於 惠 甄 麹 家 封 芮 羿 储 靳 汲 邴 糜 松 井 段 富 巫 乌 焦 巴 弓 牧 隗 山 谷 车 侯 宓 蓬 全 郗 班 仰 秋 仲 伊 宫 宁 仇 栾 暴 甘 斜 厉 戎 祖 武 符 刘 景 詹 束 龙 叶 幸 司 韶 郜 黎 蓟 薄 印 宿 白 怀 蒲 邰 从 鄂 索 咸 籍 赖 卓 蔺 屠 蒙 池 乔 阴 郁 胥 能 苍 双 闻 莘 党 翟 谭 贡 劳 逄 姬 申 扶 堵 冉 宰 郦 雍 郤 璩 桑 桂 濮 牛 寿 通 边 扈 燕 冀 郏 浦 尚 农 温 别 庄 晏 柴 瞿 阎 充 慕 连 茹 习 宦 艾 鱼 容 向 古 易 慎 戈 廖 庾 终 暨 居 衡 步 都 耿 满 弘 匡 国 文 寇 广 禄 阙 东 欧 殳 沃 利 蔚 越 夔 隆 师 巩 厍 聂 晁 勾 敖 融 冷 訾 辛 阚 那 简 饶 空 曾 毋 沙 乜 养 鞠 须 丰 巢 关 蒯 相 查 后 荆 红 游 竺 权 逑 盖 益 桓 公 万俟 司马 上官 欧阳 夏侯 诸葛 闻人 东方 赫连 皇甫 尉迟 公羊 澹台 公冶 宗政 濮阳 淳于 单于 太叔 申屠 公孙 仲孙 轩辕 令狐 锺离 宇文 长孙 慕容 鲜于 闾丘 司徒 司空 丌官 司寇 仉 督 子车 颛孙 端木 巫马 公西 漆雕 乐正 壤驷 公良 拓拔 夹谷 宰父 谷梁 晋 楚 阎 法 汝 鄢 涂 钦 段干 百里 东郭 南门 呼延 归 海 羊舌 微生 岳 帅 缑 亢 况 后 有 琴 梁丘 左丘 东门 西门 商 牟 佘 佴 伯 赏 南宫 墨 哈 谯 笪 年 爱 阳 佟 第五 言 福";
        String[] firstNames = nameString.split(" ");
        Random random = new Random();
        int index = random.nextInt(firstNames.length);
        return firstNames[index];
    }

    private Card createCard(String id, String name, String sex) {
        Card card = new Card();
        card.setAddress("中国湖南张家界");
        card.setPhoneNum(String.valueOf((int) (Math.random() * 100000000) + 10000000));
        card.setName(name);
        card.setCompany("吉首大学");
        card.setSex(sex);
        card.setTitle("普通员工");
        card.setId(id);
        card.setShareNum(0);
        card.setCollectionNum(0);
        return card;
    }

    private void randomInsertData() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        CardDao cardDao = new CardDao();
        EmployeeDao employeeDao = new EmployeeDao();
        Card card = null;
        User user = null;
        Employee employee = null;
        //已生成1-200的iD号；
        for (int i = 1; i <= 200; i++) {
            //添加男性用户
            user = new User();
            user.setId(String.valueOf(i));
            user.setPassword(String.valueOf((int) (Math.random() * 100000) + 100000));
            if (i < 100) {
                user.setRealName(getFirstName() + getBoyName());
            } else {
                user.setRealName(getFirstName() + getGirlName());
            }
            user.setUserName(user.getRealName());
            userDao.saveUser(user);
            //为每个用户添加一张名片
            if (i < 100)
                card = createCard(String.valueOf(i), user.getRealName(), "男");
            else {
                card = createCard(String.valueOf(i), user.getRealName(), "女");
            }

            cardDao.addCard(card);
            //将前五十个加入吉首大学人事部,后五十个加入财务部
            if (i < 50) {
                employee = new Employee();
                employee.setCompanyNum("jsu");
                employee.setDepartmentNum("RS");
                employee.setHireDate(new Date(System.currentTimeMillis()));
                employee.setId(String.valueOf(i));
                employee.setName(user.getRealName());
                employee.setWages(5000);
                employeeDao.addEmployee(employee);
            } else if (i >= 50) {
                employee = new Employee();
                employee.setCompanyNum("jsu");
                employee.setDepartmentNum("CWB");
                employee.setHireDate(new Date(System.currentTimeMillis()));
                employee.setId(String.valueOf(i));
                employee.setName(user.getRealName());
                employee.setWages(5000);
                employeeDao.addEmployee(employee);
            }
        }

    }

}
