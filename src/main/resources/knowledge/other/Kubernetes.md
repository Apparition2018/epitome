# Kubernetes
- 容器编排引擎
- 容器化应用程序的自动化部署、扩展和管理
---
## Reference
1. [Kubernetes Documentation](https://kubernetes.io/docs/home/)
2. [Kubernetes 文档](https://kubernetes.io/zh-cn/docs/home/)
3. [完整版Kubernetes（K8S）全套入门+微服务实战项目](https://www.bilibili.com/video/BV1MT411x7GH/)
4. [Kubernetes（k8s）入门到实战教程](https://www.bilibili.com/video/BV1GT4y1A756/)
5. [云原生Java架构师的第一课](https://www.bilibili.com/video/BV13Q4y1C7hS/)
---
## 了解 Kubernetes
### [应用部署演变](https://kubernetes.io/zh-cn/docs/concepts/overview/#going-back-in-time)
![container_evolution](https://d33wubrfki0l68.cloudfront.net/912f564f002e88449c79773b6eaae22c97e3210b/ac016/zh-cn/docs/images/container_evolution.svg)
1. 传统部署：单台或多台物理服务器
2. 虚拟化部署：单台物理服务器运行多个虚拟机
3. 容器部署：与虚拟机相似，但具有更宽松的隔离性，使应用程序之间可以共享操作系统
### [Kubernetes 功能](https://kubernetes.io/zh-cn/docs/concepts/overview/#why-you-need-kubernetes-and-what-can-it-do)
1. 服务发现和负载均衡
2. 存储编排 - storage orchestration
3. 自动部署和回滚 - automated rollouts and rollbacks
4. 自动完成装箱计算 - automatic bin packing
5. 自我修复 - self-healing
6. 密钥与配置管理 - secret and configuration management
### [Kubernetes 组件](https://kubernetes.io/zh-cn/docs/concepts/overview/components/)
![components-of-kubernetes](https://d33wubrfki0l68.cloudfront.net/2475489eaf20163ec0f54ddc1d92aa8d4c87c96b/e7c81/images/docs/components-of-kubernetes.svg)
### [Kubernetes API](https://kubernetes.io/zh-cn/docs/concepts/overview/kubernetes-api/)
### [Kubernetes 对象](https://kubernetes.io/zh-cn/docs/concepts/overview/working-with-objects/)
### [Pod](https://kubernetes.io/zh-cn/docs/concepts/workloads/pods/)

---
## 尝试 Kubernetes
### Kubernetes 基础模块
1. [创建 Kubernetes 集群](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/create-cluster/cluster-intro/)
2. [部署应用](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/create-cluster/cluster-intro/)
3. [应用探索](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/explore/explore-intro/)
4. [应用外部可见](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/expose/expose-intro/)
5. [应用可扩展](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/scale/scale-intro/)
6. [应用更新](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/update/update-intro/)
---