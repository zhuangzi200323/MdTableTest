# TextView与TableLayout显示Markdown表格对比

## 现象描述
- **TextView显示**  
  当使用`TextView`展示包含表格数据的Markdown文本时，表格部分会出现**闪烁现象**。

- **TableLayout显示**  
  当使用`TableLayout`展示相同Markdown表格数据时，表格部分**无闪烁**，显示稳定。

## 实现背景
- 数据源模拟：通过子线程读取`assets`目录下的文件，模拟网络数据流（如大模型逐字返回的场景）。
- 内容类型：Markdown文本，其中包含表格结构数据（例如`| Header | ... |`语法）。

## 可能原因分析
1. **TextView的渲染机制**  
   Markdown表格需要动态解析（如正则替换为`\t`或空格），频繁文本更新可能引发重绘闪烁。

2. **TableLayout的结构化特性**  
   表格数据被直接映射为`<TableRow>`等原生控件，避免了文本内容的频繁全量刷新。
