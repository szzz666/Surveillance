package top.szzz666.Surveillance.event;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.block.BlockPlaceEvent;
import cn.nukkit.event.block.BlockUpdateEvent;
import cn.nukkit.event.entity.EntityBlockChangeEvent;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.inventory.InventoryPickupItemEvent;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.event.level.WeatherChangeEvent;
import cn.nukkit.event.player.*;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import cn.nukkit.item.Item;
import cn.nukkit.scheduler.AsyncTask;
import top.szzz666.Surveillance.entity.SeData;

import java.sql.SQLException;

import static top.szzz666.Surveillance.SurveillanceMain.*;


public class Listeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                data.setEventType("加入服务器");
                db1.dbAdd(data);
            }
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                data.setEventType("退出服务器");
                db1.dbAdd(data);
            }
        });
    }
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                data.setEventType("执行命令");
                data.setEventOther(event.getMessage());
                db1.dbAdd(data);
            }
        });
    }
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                data.setEventType("发送消息");
                data.setEventOther(event.getMessage());
                db1.dbAdd(data);
            }
        });
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

    }

    @EventHandler
    public void playerHunger(PlayerFoodLevelChangeEvent event) {

    }

    @EventHandler
    public void playerDamage(EntityDamageEvent event) {

    }
    @EventHandler
    public void onBlockUpdate(BlockUpdateEvent event) {

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                Block block = event.getBlock();
                data.setEventType("破坏方块");
                data.setEventBlock(block.toString());
                data.setEventItem(event.getItem().toString());
                data.setEventPos(block.getBlock().getLocation().toString());
                db1.dbAdd(data);
            }
        });
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                Block block = event.getBlock();
                data.setEventType("放置方块");
                data.setEventBlock(block.toString());
                data.setEventItem(event.getItem().toString());
                data.setEventPos(block.getBlock().getLocation().toString());
                db1.dbAdd(data);
            }
        });
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    SeData data = new SeData();
                    Player player = event.getPlayer();
                    Block block = event.getBlock();
                    data.setPlayerData(player);
                    data.setEventType("加入服务器");
                    switch (event.getAction()) {
                        case LEFT_CLICK_BLOCK:
                            data.setEventType("左键点击方块");
                            break;
                        case RIGHT_CLICK_BLOCK:
                            data.setEventType("右键点击方块");
                            break;
                        case LEFT_CLICK_AIR:
                            data.setEventType("左键点击空气");
                            break;
                        case RIGHT_CLICK_AIR:
                            data.setEventType("右键点击空气");
                        default:
                            data.setEventType("其他交互");
                            break;
                    }
                    data.setEventBlock(block.toString());
                    data.setEventItem(event.getItem().toString());
                    data.setEventPos(block.getBlock().getLocation().toString());
                    db1.dbAdd(data);
                } catch (Exception ignored) {}
            }
        });

    }

    @EventHandler
    public void onEntityBlockChange(EntityBlockChangeEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                if (event.getEntity() instanceof Player player) {
                    SeData data = new SeData();
                    data.setPlayerData(player);
                    Block block = event.getTo().getBlock();
                    data.setEventType("改变方块状态");
                    data.setEventBlock(block.toString());
                    data.setEventItem(player.getInventory().getItemInHand().toString());
                    data.setEventPos(block.getBlock().getLocation().toString());
                    db1.dbAdd(data);
                }
            }
        });

    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                Entity attacker = event.getDamager();
                Entity damager = event.getEntity();
                if (attacker instanceof Player player) {
                    SeData data = new SeData();
                    data.setPlayerData(player);
                    data.setEventType("攻击实体");
                    data.setEventItem(player.getInventory().getItemInHand().toString());
                    data.setEventPos(damager.getPosition().toString());
                    data.setEventOther(damager.toString());
                    db1.dbAdd(data);
                }
            }
        });
    }


    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                SeData data = new SeData();
                Player player = event.getPlayer();
                data.setPlayerData(player);
                data.setEventType("掉落物品");
                data.setEventItem(event.getItem().toString());
                db1.dbAdd(data);
            }
        });
    }
    @EventHandler
    public void onPlayerItemPickup(InventoryPickupItemEvent event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                if (event.getInventory().getHolder() instanceof Player player) {
                    SeData data = new SeData();
                    data.setPlayerData(player);
                    data.setEventType("获得物品");
                    data.setEventItem(event.getItem().toString());
                    db1.dbAdd(data);
                }
            }
        });
    }
    @EventHandler
    public void onInventoryTransaction(InventoryTransactionEvent  event) {
        nkServer.getScheduler().scheduleAsyncTask(plugin, new AsyncTask() {
            @Override
            public void onRun() {
                try {
                    Player player = event.getTransaction().getSource();
                    for (var action : event.getTransaction().getActions()) {
                        if (action instanceof SlotChangeAction slotChangeAction) {
                            Inventory inventory = slotChangeAction.getInventory();
                            Block containerBlock = inventory.getHolder() instanceof Block ? (Block) inventory.getHolder() : Block.get(BlockID.AIR);
                            Item sourceItem = slotChangeAction.getSourceItem();
                            Item targetItem = slotChangeAction.getTargetItem();
                            SeData data = new SeData();
                            data.setPlayerData(player);
                            data.setEventType("背包库存交换");
                            data.setEventItem("源物品: " + sourceItem.toString()+ " 现物品: " + targetItem.toString());
                            data.setEventBlock(containerBlock.toString());
                            data.setEventPos(containerBlock.getLocation().toString());
                            data.setEventOther(action.toString());
                            db1.dbAdd(data);
                        }
                    }
                }catch (Exception ignored){}
            }
        });
    }
}
