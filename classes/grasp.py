from classes.knapsacksharing import KnapsackSharing
from collections import Counter
import random

class Grasp:
    def __init__(self, seed):
        self.seed = seed
		self.NUM_NEIGHBOURS = 10
        self.instance = KnapsackSharing()
		self.solution = None
		
		
	def getMinValueGroupIndex(self):
		minGroupValue = 999999999;
		minGroupIndex = 0;
		for groupIndex in self.instance.countGroups:
			if self.instance.groupValue[groupIndex] < minGroupValue:
				minGroupValue = self.instance.groupValue[groupIndex]
				minGroupIndex = groupIndex
		return minGroupIndex

			
	def hillClimbing(self, partialSolution):
		# Adaptar o hill climbing
		groupIndex = self.getMinValueGroupIndex()
		
	
	def constructiveSolution(self):
		# Gerar uma solução aleatória e construtiva
		initialSolution = []
		
	def evaluateSolution(self, partialSolution):
		# Verificar se a solução encontrada pelo método construtivo e otimizada pelo hillCLimbing é melhor que a atual (self.solution)
		return false
	
	def stopCondition(self):
		return false
		
	def grasp(self):
		while !self.stopCondition() :
			solution = self.constructiveSolution()
			solution = self.hillClimbing(solution)
			if self.evaluateSolution(solution) :
				self.solution = solution
