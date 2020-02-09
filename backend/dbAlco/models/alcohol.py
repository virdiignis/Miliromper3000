from django.db import models

from dbAlco.models.alcohol_features import Country, Producer


class AlcoholGeneralType(models.Model):
    name = models.CharField(max_length=32, primary_key=True)


class AlcoholType(models.Model):
    general_type = models.ForeignKey(AlcoholGeneralType, on_delete=models.CASCADE)
    specific_type = models.CharField(max_length=128)

    class Meta:
        unique_together = ('general_type', 'specific_type')


class Alcohol(models.Model):
    name = models.CharField(max_length=1024)
    producer = models.ForeignKey(Producer, on_delete=models.CASCADE)
    alcohol_content = models.DecimalField(decimal_places=2, max_digits=5, null=True)
    production_country = models.ForeignKey(Country, on_delete=models.SET_NULL, null=True)
    description = models.TextField(null=True)
    type = models.ForeignKey(AlcoholType, on_delete=models.CASCADE)
    average_rating = models.DecimalField(decimal_places=1, max_digits=2, default=0.0)

    class Meta:
        unique_together = ("name", "producer")

    def __str__(self):
        return self.name
