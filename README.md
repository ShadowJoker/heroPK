# heroPK
【初始版本】
英雄PK模拟器，使用Java Swing编程
三个英雄角色参与PK
每个英雄具有以下几个属性：生命值（为0时英雄倒下）、攻击力（每次攻击时扣除对方的生命值点数）、攻击间隔（每次攻击过后都要等待间隔时间才能进行下次攻击，首次攻击之前也要先等待间隔时间）
另外，每个英雄都拥有两个技能：攻击技能和防御技能，攻击技能在攻击对方时有一定概率发动，防御技能在被对方攻击时有一定概率发动，具体参数如下

BM(野蛮人)： 
生命650 攻击力40 攻击间隔4.5s    
攻击技能（跳劈）：每次攻击时有30%几率造成双倍伤害
防御技能（反弹）：每次被攻击时有30%几率把我方受到的伤害反弹给对方，例如我方被攻击，对方攻击力30，扣除我方30点生命值，如果技能发动，则对方也要扣除30点生命值
 
DH（恶魔猎人）：
生命600 攻击力30 攻击间隔3s
攻击技能（吸血）：每次攻击时有30%几率把造成的伤害转变为自己的生命值（对被攻击者造成伤害，并且将攻击伤害转变成自己的生命值），但不能超过上限，例如我方攻击，扣除对方30的生命值，同时给自己增加30点生命值
防御技能（闪避）：每次被攻击时有30%几率闪避不受伤害
 
MK（武僧）：
生命700 攻击力50 攻击间隔6.0s
攻击技能（重击）：每次攻击时有25%几率造成对方眩晕8s的效果（对方受到伤害后附加眩晕），对方英雄眩晕期间不能发起攻击，只能挨打，被攻击时也不能发起防御技能，且眩晕结束后对方英雄要重新等待攻击间隔，眩晕时间不能叠加，如果对方已经处于眩晕，我方又发动攻击技能，那么对方眩晕时间重新开始计算
防御技能（天神）：每次被攻击时有60%的几率防御一半伤害，例如我方被攻击，对方攻击力为40，如果技能发动，则只扣除我方20点生命值
 
1.程序启动后，监听控制台输入
2.输入任意两个英雄名称(逗号分隔)发起PK，格式：BM,DH
3.系统输出PK详细过程，直到有一方胜出，格式如下：
BM攻击DH，BM发动攻击技能，DH未发动防御技能，BM:350->350，DH:280->200
....
BM胜出

【2015.06.22 更新 新英雄登场！！！圣骑士与元素法师】

GD(圣骑士)： 
生命750 攻击力40 攻击间隔5.0s
攻击技能（惩戒）：每次攻击时有40%几率造成打断（使敌人停顿2秒并且重置攻击间隔）
防御技能（圣盾）：每次被攻击时有50%的几率格挡掉20点伤害。如果受到伤害小于20，则免除此次伤害。

MG(元素法师)： 
生命500 攻击力20 攻击间隔2.5s
攻击技能（火球）：每次攻击时有60%几率造成30点伤害额外伤害
防御技能（冰盾）：每次被攻击时有30%几率使敌人造成冰冻并且免疫本次伤害（使敌人5秒内不能攻击，结束后敌人会立刻攻击一次）

【2015.08.01】准备移植到Android平台，游戏取名《英雄大乱斗》
初步定3个功能：英雄闯关，英雄PK，英雄堂。
后期准备加入闯关模式，而且可以在战斗中获得点数，升级英雄，每个英雄分为五段（五颗星），初级、高级、专家级、大师级、传奇级。而且有新英雄可以用点数解锁。

【2015.08.16】Android平台移植完成，可顺利运行。英雄闯关和英雄堂两个模块尚未开发完成。

【2015.08.28】修复了一些例如两个人同时死亡，计时器错误等小BUG
