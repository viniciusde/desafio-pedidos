CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL,
  `categoria` varchar(50) COLLATE latin1_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `bairro` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `cep` varchar(8) COLLATE latin1_general_ci DEFAULT NULL,
  `cidade` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `email` varchar(100) COLLATE latin1_general_ci DEFAULT NULL,
  `estado` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `nome` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `rua` varchar(200) COLLATE latin1_general_ci DEFAULT NULL,
  `senha` varchar(255) COLLATE latin1_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `sessao` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `pedido_itens` (
  `id_item` int(11) NOT NULL,
  `quantidade` decimal(19,2) DEFAULT NULL,
  `sub_total` decimal(19,2) DEFAULT NULL,
  `valor` decimal(19,2) DEFAULT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  `id_produto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `produtos` (
  `id_produto` int(11) NOT NULL,
  `descricao` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `foto` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `produto` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`);

ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`);

ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `FKdnomiluem4t3x66t6b9aher47` (`id_cliente`);

ALTER TABLE `pedido_itens`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `FKrgh7l50r1judvc3ok9y8sfrv3` (`id_pedido`),
  ADD KEY `FK83345b0ifop00o8g4ttonqm77` (`id_produto`);
  
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`id_produto`),
  ADD KEY `FKbq4e9crnlcsvm7ehu147wuavt` (`id_categoria`);

ALTER TABLE `categorias`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pedido_itens`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `produtos`
  MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `pedidos`
  ADD CONSTRAINT `FKdnomiluem4t3x66t6b9aher47` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);

ALTER TABLE `pedido_itens`
  ADD CONSTRAINT `FK83345b0ifop00o8g4ttonqm77` FOREIGN KEY (`id_produto`) REFERENCES `produtos` (`id_produto`),
  ADD CONSTRAINT `FKrgh7l50r1judvc3ok9y8sfrv3` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`);

ALTER TABLE `produtos`
  ADD CONSTRAINT `FKbq4e9crnlcsvm7ehu147wuavt` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`);
COMMIT;