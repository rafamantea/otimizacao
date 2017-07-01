class KnapsackSharing:
    def __init__(self):
        self.countItems = None  # 1st line
        self.countGroups = None  # 2nd line
        self.capacity = None  # 3rd line
        self.countItemsByGroups = None  # 4th line
        self.items = None  # Other lines
        self.seed = None  # Random seed
		self.groupValue = None # Value of the group

    def readFile(self, instanceFile):
        file = open(instanceFile, 'r')
        self.countItems = int(file.readline())
        self.countGroups = int(file.readline())
        self.capacity = int(file.readline())
        self.countItemsByGroups = (file.readline()).split()

        # ITEMS DATA STRUCT: List (groups) of lists (items) of tuples (weight|value)
        self.items = []

        # Inicializing items struct
        for i in range(self.countGroups):
            self.items.append([])

        groupIndex = 0

        for countItems in self.countItemsByGroups:
            for i in range(int(countItems)):
                item = (file.readline()).split()
                self.items[groupIndex].append((int(item[0]), int(item[1])))
				self.groupValue[groupIndex] += int(item[1])
            groupIndex += 1

        file.close()

    def toGlpkData(self):
        return
