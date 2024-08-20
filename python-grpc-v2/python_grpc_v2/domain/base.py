import uuid
from typing import TypeVar

T = TypeVar("T")


class Entity:
    def __init__(self, entity_id: T):
        self.entity_id = entity_id

    def __eq__(self, other):
        if isinstance(other, Entity):
            return self.entity_id == other.entity_id
        return False

    def __hash__(self):
        return hash(self.entity_id)


class AggregateRoot(Entity):
    def __init__(self, entity_id: T):
        super().__init__(entity_id)
