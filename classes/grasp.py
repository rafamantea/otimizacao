from classes.knapsacksharing import KnapsackSharing
from collections import Counter
from random import randint

class Grasp:
    def __init__(self, seed):
        self.seed = seed
		self.NUM_NEIGHBOURS = 10
		self.NUM_SELECTED_ITEMS = 10
		self.MAX_ITERATIONS = 10
        self.instance = KnapsackSharing()
		self.solution = []
		self.solutionWeight = 0
		
	####
	# Retorna o indice do grupo com o menor lucro
	####
	def getMinValueGroupIndex(self):
		minGroupValue = 999999999;
		minGroupIndex = 0;
		for groupIndex in self.instance.countGroups:
			if self.instance.groupValue[groupIndex] < minGroupValue:
				minGroupValue = self.instance.groupValue[groupIndex]
				minGroupIndex = groupIndex
		return minGroupIndex

	####	
	# Retorna o indice do item com a melhor relação Lucro/Peso entre 5 items aleatórios que não estejam na solução atual
	####
	def getBestItem(self):
		groupIndex = self.getMinValueGroupIndex()
		selected = 0
		selectedItemStrenght = 0 #relação lucro/peso do item mais benefico
		selectedItemIndex = 0
		while selected < self.NUM_SELECTED_ITEMS:
			itemIndex = randint(0, self.countItemsByGroups[groupIndex])
			if self.instance.items[groupIndex][itemIndex][2] == 0:	# verifica se o item não pertence a solução atual
				selected = selected + 1
				itemWeight = self.instance.items[groupIndex][itemIndex][0]
				itemValue = self.instance.items[groupIndex][itemIndex][1]
				itemStrenght = (itemValue / itemWeight)
				if  itemStrenght >= selectedItemStrenght:
					selectedItemStrenght = itemStrenght
					selectedItemIndex = itemIndex
		return selectedItemIndex
			
	####
	# Adiciona o item com a melhor relação Lucro/Peso entre 5 itens aleatórios que não estejam na solução atual
	####
	def insertBestItem(self):
		groupIndex = self.getMinValueGroupIndex()
		bestItemIndex = self.getBestItem()
		
		itemWeight = self.instance.items[groupIndex][itemIndex][0]
		newWeight = self.solutionWeight + itemWeight
		if newWeight <= self.instance.capacity
			self.instance.items[groupIndex][itemIndex][2] = 1	# Marca o item como selecionado na solução
			self.solutionWeight = newWeight
			# Adicionar item na solução
				
	####
	# Cria uma solução inicial semi-aleatória
	####
	def initialSolution(self):
		groupIndex = self.getMinValueGroupIndex()
		while self.solutionWeight < self.instance.capacity :
			self.insertBestItem()
			
			
	def hillClimbing(self, partialSolution):
		# Adaptar o hill climbing
		groupIndex = self.getMinValueGroupIndex()
		
	def constructiveSolution(self):
		groupIndex = self.getMinValueGroupIndex()
		for randomItem in 
		
	def evaluateSolution(self, partialSolution):
		# Verificar se a solução encontrada pelo método construtivo e otimizada pelo hillCLimbing é melhor que a atual (self.solution)
		return false
	
	def stopCondition(self):
		return false
		
	def grasp(self):
		self.initialSolution()	# Gera a solução inicial
		iterationsInRow = 0
		while iterationsInRow < self.MAX_ITERATIONS :	# Condicao de parada: 10 iterações sem melhorar a solução
			solution = self.constructiveSolution()
			solution = self.hillClimbing(solution)
			if self.evaluateSolution(solution) :
				self.solution = solution	# Atualizar a solução
				iterationsInRow = 0
			else:
				iterationsInRow = iterationsInRow + 1
