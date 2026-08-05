# mall

---
## 商品
- SPU (Product)：Standard Product Unit，标准产品单元
    - pId、商品标题、描述、品牌、型号、主图
    - status：草稿 → 待审核 → 已上架 → 已下架 → 已删除
- SKU：Stock Keeping Unit，库存量单位
    - skuId、properties（属性组合）、stocks（库存）、price（价格）
- category：分类表，三级分类
    - parent_id, grade
- prod_prop：属性定义表
- prod_prop_value：属性值表
- ProdPropRule
    - SPEC：规则属性 → SKU
    - ATTRIBUTE：规格参数 → 搜索时与分类关联搜索
### 考点
- 商品上下架怎么保持数据一致性：更新数据库后，删除商品缓存、相关购物车缓存
---
## 购物车
- basket：shopId, skuId, count
- ShopCartDto
    - List<ShopCartItemDto>
### 考点
- 购物车的数量加减逻辑：delta（增量）设计，只传 ±1
- 购物车缓存保持一致性：
    1. 用户操作触发：增删改 → `@CacheEvict`
    2. 后台编辑商品：更新数据库后，删除商品缓存、相关购物车缓存
- 立即购买和购物车结算：立即购买可临时组装一个 ShopCartItemDto (baskedId = -1L)，放入 Collections.singletonList()，进入统一的下单流程
---
